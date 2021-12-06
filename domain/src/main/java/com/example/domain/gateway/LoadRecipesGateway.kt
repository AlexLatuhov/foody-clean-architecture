package com.example.domain.gateway

import com.example.domain.models.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface LoadRecipesGateway {

    fun loadDataFromCache(searchQuery: String?): Flow<List<RecipeDomain>?>

}