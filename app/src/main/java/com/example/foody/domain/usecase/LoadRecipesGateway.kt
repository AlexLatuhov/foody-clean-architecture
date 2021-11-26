package com.example.foody.domain.usecase

import com.example.foody.domain.models.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface LoadRecipesGateway {
    fun loadDataFromCache(searchQuery: String?): Flow<List<RecipeDomain>?>
}