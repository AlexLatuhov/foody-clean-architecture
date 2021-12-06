package com.example.data.database.repositories

import com.example.data.Constants.Companion.DEFAULT_ID
import com.example.data.database.RecipesDao
import com.example.data.database.models.RecipesEntity
import com.example.data.repositories.RecipesSaver
import javax.inject.Inject

class LocalRecipesSaver @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesSaver {

    override fun insertRecipes(recipesEntity: RecipesEntity) =
        recipesDao.insertRecipes(recipesEntity).compareTo(DEFAULT_ID) == 0
}