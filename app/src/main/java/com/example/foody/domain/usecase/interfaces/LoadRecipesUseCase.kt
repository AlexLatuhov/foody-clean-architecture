package com.example.foody.domain.usecase.interfaces

import com.example.foody.domain.models.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface LoadRecipesUseCase {

    fun loadDataFromCache(searchQuery: String? = null): Flow<List<RecipeDomain>?>

}