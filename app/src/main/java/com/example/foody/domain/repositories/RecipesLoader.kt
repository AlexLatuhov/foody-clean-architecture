package com.example.foody.domain.repositories

import com.example.foody.data.database.models.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesLoader {
    fun readRecipes(): Flow<List<RecipesEntity>>
}