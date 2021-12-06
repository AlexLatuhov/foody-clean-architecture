package com.example.data.repositories

import com.example.data.database.models.RecipesEntity

interface RecipesSaver {

    fun insertRecipes(recipesEntity: RecipesEntity): Boolean

}