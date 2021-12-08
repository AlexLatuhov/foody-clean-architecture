package com.example.domain.usecase.interfaces

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult

interface InsertFavoriteRecipeUseCase {

    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain): OperationResult

}