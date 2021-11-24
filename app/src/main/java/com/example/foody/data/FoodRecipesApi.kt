package com.example.foody.data

import com.example.foody.data.api.models.RecipeDataItem
import com.example.foody.data.database.models.FoodJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecipesApi {
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>): Response<RecipeDataItem>

    @GET("food/jokes/random")
    suspend fun getFoodJoke(@Query("apiKey") apiKey: String): Response<FoodJoke>
}