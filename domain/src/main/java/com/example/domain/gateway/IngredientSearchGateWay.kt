package com.example.domain.gateway

import com.example.domain.models.IngredientSearchDomain
import kotlinx.coroutines.flow.Flow

interface IngredientSearchGateWay {
    fun getIngredientsSearchResult(searchQuery: String): Flow<IngredientSearchDomain>
}