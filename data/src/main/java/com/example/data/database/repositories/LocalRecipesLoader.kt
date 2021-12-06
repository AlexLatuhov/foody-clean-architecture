package com.example.data.database.repositories

import com.example.data.database.RecipesDao
import com.example.data.repositories.RecipesLoader
import javax.inject.Inject

class LocalRecipesLoader @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesLoader {

    override fun readRecipes() = recipesDao.readRecipes()

    override fun readFavoriteRecipes() = recipesDao.readFavoriteRecipes()
}