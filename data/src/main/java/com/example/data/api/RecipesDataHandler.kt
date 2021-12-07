package com.example.data.api

import android.content.Context
import android.util.Log
import com.example.data.Constants
import com.example.data.R
import com.example.data.api.models.FoodJokeDataItem
import com.example.data.api.models.RecipeDataItem
import com.example.data.extentions.getErrorMessage
import com.example.data.mappers.convertToLocalDbItem
import com.example.data.repositories.RecipesSaver
import com.example.domain.models.request.RecipesDataRequestResult
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class RecipesDataHandler @Inject constructor(
    @ApplicationContext private val context: Context,
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
                    foodRecipe.convertToLocalDbItem(context.getString(R.string.no_value))
                val insertResult = localDataSource.insertRecipes(domainData)
                Log.d(
                    Constants.TEST_TAG,
                    "insertRecipes ${domainData.foodRecipeEntity.recipeItemEntities.size}, result is $insertResult"
                )
                if (!insertResult) {
                    return RecipesDataRequestResult.Error(context.getString(R.string.unknown_error))
                }
            } else {
                return RecipesDataRequestResult.Error(context.getString(R.string.no_data))
            }
        }
        return result
    }

    suspend fun getFoodJoke(api: String): Response<FoodJokeDataItem> =
        foodRecipesApi.getFoodJoke(api)

    private fun Response<RecipeDataItem>.getRecipesResult(): RecipesDataRequestResult {
        val error = getErrorMessage(context)
        return when {
            error != null -> {
                RecipesDataRequestResult.Error(error)
            }
            body()!!.results.isNullOrEmpty() -> {
                RecipesDataRequestResult.Error(context.getString(R.string.not_found))
            }
            else -> {
                RecipesDataRequestResult.Success
            }
        }
    }
}