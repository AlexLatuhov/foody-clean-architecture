package com.example.data.recipes

import com.example.data.database.models.RecipesEntity
import com.example.data.repositories.RecipesSaver
import com.example.domain.models.request.OperationResult

class FakeRecipesSaver : RecipesSaver {

    var testResult: OperationResult = OperationResult.Success()

    override suspend fun insertRecipes(recipesEntity: RecipesEntity) = testResult
}