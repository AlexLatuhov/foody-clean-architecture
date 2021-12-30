package com.example.data.recipes

import com.example.data.api.FoodRecipesApi
import com.example.data.api.models.FoodJokeDataItem
import com.example.data.api.models.RecipeDataItem
import retrofit2.Response

class FakeFoodRecipesApi : FoodRecipesApi {

    var testResponse: Response<RecipeDataItem> = Response.success(RecipeDataItem(emptyList()))

    override suspend fun getRecipes(queries: Map<String, String>): Response<RecipeDataItem> =
        testResponse

    override suspend fun getFoodJoke(apiKey: String): Response<FoodJokeDataItem> {
        TODO("Not yet implemented")
    }
}