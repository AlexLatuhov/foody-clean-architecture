package com.example.domain.usecase.interfaces

import com.example.domain.models.request.FavOperationResult

interface DeleteAllFavoriteRecipeUseCase {

    suspend fun deleteAll(): FavOperationResult

}