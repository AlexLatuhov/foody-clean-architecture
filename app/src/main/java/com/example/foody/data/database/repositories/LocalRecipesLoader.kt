package com.example.foody.data.database.repositories

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.models.RecipesEntity
import com.example.foody.domain.repositories.RecipesLoader
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRecipesLoader @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesLoader {
    override fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }
}