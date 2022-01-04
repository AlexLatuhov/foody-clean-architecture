package com.example.presentation.favorites

import com.example.domain.models.request.OperationResult
import com.example.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase

class FakeDeleteAllFavoriteRecipeUseCase : DeleteAllFavoriteRecipeUseCase {
    override suspend fun deleteAll(): OperationResult {
        TODO("Not yet implemented")
    }
}