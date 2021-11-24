package com.example.foody.domain.datamanage

import com.example.foody.domain.database.RecipesDao
import com.example.foody.domain.models.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRecipesLoader @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesLoader {
    override fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }
}