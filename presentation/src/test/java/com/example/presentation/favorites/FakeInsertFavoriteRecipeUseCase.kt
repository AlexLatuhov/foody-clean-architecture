package com.example.presentation.favorites

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult
import com.example.domain.usecase.interfaces.InsertFavoriteRecipeUseCase

class FakeInsertFavoriteRecipeUseCase(var operationResult: OperationResult = OperationResult.Success()) :
    InsertFavoriteRecipeUseCase {

    override suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        operationResult
}