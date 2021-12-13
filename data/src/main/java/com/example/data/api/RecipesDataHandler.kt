package com.example.data.api

import com.example.data.api.models.FoodJokeDataItem
import com.example.data.api.models.RecipeDataItem
import com.example.data.extentions.wasKeyLimited
import com.example.data.extentions.wasTimeout
import com.example.data.mappers.convertToLocalDbItem
import com.example.data.repositories.RecipesSaver
import com.example.domain.models.request.OperationResult
import com.example.domain.models.request.RecipesDataRequestResult
import retrofit2.Response
import javax.inject.Inject

class RecipesDataHandler @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi,
    private val localDataSource: RecipesSaver
) {

    suspend fun getRecipes(queries: Map<String, String>): RecipesDataRequestResult {
        val dataResponse = foodRecipesApi.getRecipes(queries)
        val result = dataResponse.getRecipesResult()
        val foodRecipe = dataResponse.body()
        if (result is RecipesDataRequestResult.Success) {
            if (foodRecipe != null) {
                val domainData =
                    foodRecipe.convertToLocalDbItem()
                if (localDataSource.insertRecipes(domainData) is OperationResult.Fail) {
                    return RecipesDataRequestResult.UnknownError
                }
            } else {
                return RecipesDataRequestResult.NoData
            }
        }
        return result
    }

    suspend fun getFoodJoke(api: String): Response<FoodJokeDataItem> =
        foodRecipesApi.getFoodJoke(api)

    private fun Response<RecipeDataItem>.getRecipesResult(): RecipesDataRequestResult {
        return when {
            wasTimeout() -> {
                RecipesDataRequestResult.Timeout
            }
            wasKeyLimited() -> {
                RecipesDataRequestResult.ApiKetLimited
            }
            isSuccessful -> {
                RecipesDataRequestResult.Success
            }
            body()!!.results.isNullOrEmpty() -> {
                RecipesDataRequestResult.NotFound
            }
            else -> {
                RecipesDataRequestResult.ErrorWithMessage(message())
            }
        }
    }
}