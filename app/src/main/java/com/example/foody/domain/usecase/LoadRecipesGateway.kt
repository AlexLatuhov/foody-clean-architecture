package com.example.foody.domain.usecase

import com.example.foody.data.database.models.Recipe
import kotlinx.coroutines.flow.Flow

interface LoadRecipesGateway {
    fun loadDataFromCache(searchQuery: String?): Flow<List<Recipe>?>
}