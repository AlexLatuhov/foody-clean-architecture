package com.example.data.database.repositories

import com.example.data.Constants.Companion.DEFAULT_ID
import com.example.data.database.RecipesDao
import com.example.data.database.models.RecipesEntity
import com.example.data.repositories.RecipesSaver
import com.example.domain.models.request.OperationResult
import javax.inject.Inject

class LocalRecipesSaver @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesSaver {

    override suspend fun insertRecipes(recipesEntity: RecipesEntity) =
        if (recipesDao.insertRecipes(recipesEntity)
                .compareTo(DEFAULT_ID) == 0
        ) OperationResult.Success() else OperationResult.Fail

}