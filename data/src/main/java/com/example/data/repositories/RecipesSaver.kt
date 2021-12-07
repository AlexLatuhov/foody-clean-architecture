package com.example.data.repositories

import com.example.data.database.models.RecipesEntity

interface RecipesSaver {

    suspend fun insertRecipes(recipesEntity: RecipesEntity): Boolean

}