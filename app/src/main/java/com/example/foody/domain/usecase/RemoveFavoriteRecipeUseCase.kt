package com.example.foody.domain.usecase

import com.example.foody.domain.gateway.RemoveFavoriteRecipeGateWay
import com.example.foody.domain.models.FavoritesEntityDomain
import javax.inject.Inject

class RemoveFavoriteRecipeUseCase @Inject constructor(
    private val removeFavoriteRecipeGateWay: RemoveFavoriteRecipeGateWay
) {
    suspend fun removeFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        removeFavoriteRecipeGateWay.deleteFavoriteRecipe(favoritesEntity)
}
