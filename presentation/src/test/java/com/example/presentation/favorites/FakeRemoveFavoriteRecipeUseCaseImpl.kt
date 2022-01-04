package com.example.presentation.favorites

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult
import com.example.domain.usecase.interfaces.RemoveFavoriteRecipeUseCase

class FakeRemoveFavoriteRecipeUseCaseImpl : RemoveFavoriteRecipeUseCase {
    override suspend fun removeFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): OperationResult {
        TODO("Not yet implemented")
    }
}