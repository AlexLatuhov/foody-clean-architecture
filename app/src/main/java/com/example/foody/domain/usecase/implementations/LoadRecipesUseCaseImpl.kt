package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.LoadRecipesGateway
import com.example.foody.domain.usecase.interfaces.LoadRecipesUseCase
import javax.inject.Inject

class LoadRecipesUseCaseImpl @Inject constructor(
    private val loadRecipesGateway: LoadRecipesGateway
) : LoadRecipesUseCase {

    override fun loadDataFromCache(searchQuery: String?) =
        loadRecipesGateway.loadDataFromCache(searchQuery)

}
