package com.example.foody.data.database.repositories

import com.example.foody.data.database.RecipesDao
import com.example.foody.domain.repositories.RecipesLoader
import javax.inject.Inject

class LocalRecipesLoader @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesLoader {
    override fun readRecipes() = recipesDao.readRecipes()
    override fun readFavoriteRecipes() = recipesDao.readFavoriteRecipes()
}