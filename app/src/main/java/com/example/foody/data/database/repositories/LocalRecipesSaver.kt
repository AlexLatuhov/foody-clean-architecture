package com.example.foody.data.database.repositories

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.models.RecipesEntity
import com.example.foody.domain.repositories.RecipesSaver
import javax.inject.Inject

class LocalRecipesSaver @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesSaver {
    override fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}