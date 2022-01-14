package com.example.domain.usecase.interfaces

import com.example.domain.models.IngredientSearchDomain
import kotlinx.coroutines.flow.Flow

interface IngredientSearchUseCase {
    fun getIngredientsSearchResult(searchQuery: String): Flow<IngredientSearchDomain>
}