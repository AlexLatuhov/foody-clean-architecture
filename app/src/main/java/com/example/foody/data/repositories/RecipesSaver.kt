package com.example.foody.data.repositories

import com.example.foody.data.database.models.RecipesEntity

interface RecipesSaver {

    fun insertRecipes(recipesEntity: RecipesEntity): Boolean

}