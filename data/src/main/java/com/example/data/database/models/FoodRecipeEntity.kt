package com.example.data.database.models

import com.google.gson.annotations.SerializedName

data class FoodRecipeEntity(
    @SerializedName("results")
    val recipeItemEntities: List<RecipeItemEntity>
)