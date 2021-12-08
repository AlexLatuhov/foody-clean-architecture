package com.example.data.repositories

import com.example.data.database.models.RecipesEntity
import com.example.domain.models.request.OperationResult

interface RecipesSaver {

    suspend fun insertRecipes(recipesEntity: RecipesEntity): OperationResult

}