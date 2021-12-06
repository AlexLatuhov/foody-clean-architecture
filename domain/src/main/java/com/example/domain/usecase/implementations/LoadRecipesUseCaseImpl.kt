package com.example.domain.usecase.implementations

import com.example.domain.gateway.LoadRecipesGateway
import com.example.domain.usecase.interfaces.LoadRecipesUseCase
import javax.inject.Inject

class LoadRecipesUseCaseImpl @Inject constructor(
    private val loadRecipesGateway: LoadRecipesGateway
) : LoadRecipesUseCase {

    override fun loadDataFromCache(searchQuery: String?) =
        loadRecipesGateway.loadDataFromCache(searchQuery)

}
