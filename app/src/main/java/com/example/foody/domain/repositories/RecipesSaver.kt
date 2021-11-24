package com.example.foody.domain.repositories

import com.example.foody.data.database.models.RecipesEntity

interface RecipesSaver {
    fun insertRecipes(recipesEntity: RecipesEntity)
}