package com.example.foody.domain.gateway

import com.example.foody.domain.models.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface LoadRecipesGateway {
    fun loadDataFromCache(searchQuery: String?): Flow<List<RecipeDomain>?>
}