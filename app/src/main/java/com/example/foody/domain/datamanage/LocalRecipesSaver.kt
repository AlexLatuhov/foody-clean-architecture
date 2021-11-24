package com.example.foody.domain.datamanage

import com.example.foody.domain.database.RecipesDao
import com.example.foody.domain.models.RecipesEntity
import javax.inject.Inject

class LocalRecipesSaver @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesSaver {
    override fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}