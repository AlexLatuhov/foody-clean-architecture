package com.example.domain.usecase.interfaces

import com.example.domain.models.request.OperationResult

interface DeleteAllFavoriteRecipeUseCase {

    suspend fun deleteAll(): OperationResult

}