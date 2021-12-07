package com.example.domain.usecase.interfaces

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.FavOperationResult

interface InsertFavoriteRecipeUseCase {

    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain): FavOperationResult

}