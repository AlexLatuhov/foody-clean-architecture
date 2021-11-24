package com.example.foody.domain.usecase

import com.example.foody.data.database.models.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadRecipesUseCase @Inject constructor(
    private val loadRecipesGateway: LoadRecipesGateway
) {
    fun loadDataFromCache(searchQuery: String? = null): Flow<List<Result>?> {
        return loadRecipesGateway.loadDataFromCache(searchQuery)
    }
}
