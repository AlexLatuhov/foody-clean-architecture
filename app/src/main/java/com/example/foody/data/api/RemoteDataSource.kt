package com.example.foody.data.api

import android.content.Context
import android.util.Log
import com.example.foody.R
import com.example.foody.data.Constants
import com.example.foody.data.DataToLocalDbMapper
import com.example.foody.data.api.models.FoodJokeDataItem
import com.example.foody.data.api.models.RecipeDataItem
import com.example.foody.data.getErrorMessage
import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.repositories.RecipesSaver
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
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
                    return DataRequestResult.Error(context.getString(R.string.unknown_error))
                }
            } else {
                return DataRequestResult.Error(context.getString(R.string.no_data))
            }
        }
        return result
    }

    suspend fun getFoodJoke(api: String): Response<FoodJokeDataItem> =
        foodRecipesApi.getFoodJoke(api)

    private fun Response<RecipeDataItem>.getRecipesResult(): DataRequestResult {
        val error = getErrorMessage(context)
        return when {
            error != null -> {
                DataRequestResult.Error(error)
            }
            body()!!.results.isNullOrEmpty() -> {
                DataRequestResult.Error(context.getString(R.string.not_found))
            }
            else -> {
                DataRequestResult.Success
            }
        }
    }
}