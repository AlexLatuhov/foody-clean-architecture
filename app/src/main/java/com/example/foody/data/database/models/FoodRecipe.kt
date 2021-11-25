package com.example.foody.data.database.models

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val recipes: List<Recipe>
)