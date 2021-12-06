package com.example.domain.usecase.interfaces

import com.example.domain.models.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface LoadRecipesUseCase {

    fun loadDataFromCache(searchQuery: String? = null): Flow<List<RecipeDomain>?>

}