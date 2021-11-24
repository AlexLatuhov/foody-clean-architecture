package com.example.foody.data

import android.util.Log
import com.example.foody.data.models.RecipeDataItem
import com.example.foody.domain.datamanage.RecipesSaver
import com.example.foody.domain.models.DataRequestResult
import com.example.foody.domain.models.FoodJoke
import com.example.foody.presentation.util.Constants
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi,
    private val dataToDomainMapper: DataToDomainMapper,
    private val localDataSource: RecipesSaver
) {
    suspend fun getRecipes(queries: Map<String, String>): DataRequestResult {
        val dataResponse = foodRecipesApi.getRecipes(queries)
        val result = dataResponse.getRecipesResult()
        val foodRecipe = dataResponse.body()
        if (result is DataRequestResult.Success) {
            if (foodRecipe != null) {
                val domainData = dataToDomainMapper.map(foodRecipe)
                Log.d(Constants.TEST_TAG, "insertRecipes ${domainData.foodRecipe.results.size}")
                localDataSource.insertRecipes(domainData)
            } else {
                return DataRequestResult.Error("No data")
            }
        }
        return result
    }

    suspend fun getFoodJoke(api: String): Response<FoodJoke> {//todo complete
        return foodRecipesApi.getFoodJoke(api)
    }

    private fun Response<RecipeDataItem>.getRecipesResult(): DataRequestResult {
        when {
            message().toString().contains("timeout") -> {
                return DataRequestResult.Error("Timeout")
            }
            code() == 402 -> {
                return DataRequestResult.Error("API Key Limited")
            }
            body()!!.results.isNullOrEmpty() -> {
                return DataRequestResult.Error("Recipes not found")
            }
            isSuccessful -> {
                return DataRequestResult.Success
            }
            else -> {
                return DataRequestResult.Error(message())
            }
        }
    }
}