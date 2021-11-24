package com.example.foody.domain.datamanage

import com.example.foody.domain.models.RecipesEntity

interface RecipesSaver {
    fun insertRecipes(recipesEntity: RecipesEntity)
}