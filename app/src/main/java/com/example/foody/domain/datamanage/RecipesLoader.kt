package com.example.foody.domain.datamanage

import com.example.foody.domain.models.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesLoader {
    fun readRecipes(): Flow<List<RecipesEntity>>
}