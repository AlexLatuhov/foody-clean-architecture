package com.example.foody.data.api

import android.util.Log
import com.example.foody.data.Constants
import com.example.foody.data.DataToLocalDbMapper
import com.example.foody.data.FoodRecipesApi
import com.example.foody.data.api.models.FoodJokeDataItem
import com.example.foody.data.api.models.RecipeDataItem
import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.repositories.RecipesSaver
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi,
    private val dataToDomainMapper: DataToLocalDbMapper,
    private val localDataSource: RecipesSaver
) {
    suspend fun getRecipes(queries: Map<String, String>): DataRequestResult {
        val dataResponse = foodRecipesApi.getRecipes(queries)
        val result = dataResponse.getRecipesResult()
        val foodRecipe = dataResponse.body()
        if (result is DataRequestResult.Success) {
            if (foodRecipe != null) {
                val domainData = dataToDomainMapper.map(foodRecipe)
                val insertResult = localDataSource.insertRecipes(domainData)
                Log.d(
                    Constants.TEST_TAG,
                    "insertRecipes ${domainData.foodRecipe.recipes.size}, result is $insertResult"
                )
                if (!insertResult) {
                    return DataRequestResult.Error("Error")
                }
            } else {
                return DataRequestResult.Error("No data")
            }
        }
        return result
    }

    suspend fun getFoodJoke(api: String): Response<FoodJokeDataItem> =
        foodRecipesApi.getFoodJoke(api)

    private fun Response<RecipeDataItem>.getRecipesResult(): DataRequestResult {
        return when {
            message().toString().contains("timeout") -> {
                DataRequestResult.Error("Timeout")
            }
            code() == 402 -> {
                DataRequestResult.Error("API Key Limited")
            }
            body()!!.results.isNullOrEmpty() -> {
                DataRequestResult.Error("Not found")
            }
            isSuccessful -> {
                DataRequestResult.Success
            }
            else -> {
                DataRequestResult.Error(message())
            }
        }
    }
}