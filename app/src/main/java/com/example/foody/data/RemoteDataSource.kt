package com.example.foody.data

import com.example.foody.data.network.FoodRecipesApi
import com.example.foody.models.FoodJoke
import com.example.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    //todo remove code duplication
    suspend fun searchRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(queries)
    }

    suspend fun getFoodJoke(api: String): Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(api)
    }
}