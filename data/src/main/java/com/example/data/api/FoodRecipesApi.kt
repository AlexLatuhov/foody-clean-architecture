package com.example.data.api

import com.example.data.api.models.FoodJokeDataItem
import com.example.data.api.models.RecipeDataItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queries: Map<String, String>): Response<RecipeDataItem>

    @GET("food/{path}")
    suspend fun getFoodJoke(
        @Path(value = "path", encoded = true) path: String,
        @Query("apiKey") apiKey: String
    ): Response<FoodJokeDataItem>
}