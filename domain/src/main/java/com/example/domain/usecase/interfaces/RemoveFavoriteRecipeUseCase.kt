package com.example.domain.usecase.interfaces

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult

interface RemoveFavoriteRecipeUseCase {

    suspend fun removeFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): OperationResult

}