package com.example.data.recipes

import com.example.data.database.models.RecipesEntity
import com.example.data.repositories.RecipesSaver
import com.example.domain.models.request.OperationResult

class FakeRecipesSaver(var testResult: OperationResult = OperationResult.Success()) : RecipesSaver {
    override suspend fun insertRecipes(recipesEntity: RecipesEntity) = testResult
}