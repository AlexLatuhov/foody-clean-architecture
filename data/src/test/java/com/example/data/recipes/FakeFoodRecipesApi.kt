package com.example.data.recipes

import com.example.data.api.FoodRecipesApi
import com.example.data.api.models.ExtendedIngredientDataItem
import com.example.data.api.models.FoodJokeDataItem
import com.example.data.api.models.IngredientSearchResponse
import com.example.data.api.models.RecipeDataItem
import retrofit2.Response

class FakeFoodRecipesApi(
    var testResponse: Response<RecipeDataItem> = Response.success(
        RecipeDataItem(emptyList())
    )
) : FoodRecipesApi {

    override suspend fun getRecipes(queries: Map<String, String>) = testResponse

    override suspend fun getFoodJoke(path: String, apiKey: String): Response<FoodJokeDataItem> {
        TODO("Not yet implemented")
    }

    override suspend fun getIngredientInfo(
        id: String,
        apiKey: String
    ): Response<ExtendedIngredientDataItem> {
        TODO("Not yet implemented")
    }

    override suspend fun ingredientSearch(queries: Map<String, String>): Response<IngredientSearchResponse> {
        TODO("Not yet implemented")
    }
}