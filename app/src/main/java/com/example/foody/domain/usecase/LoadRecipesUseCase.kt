package com.example.foody.domain.usecase

import javax.inject.Inject

class LoadRecipesUseCase @Inject constructor(
    private val loadRecipesGateway: LoadRecipesGateway
) {
    fun loadDataFromCache(searchQuery: String? = null) =
        loadRecipesGateway.loadDataFromCache(searchQuery)
}
