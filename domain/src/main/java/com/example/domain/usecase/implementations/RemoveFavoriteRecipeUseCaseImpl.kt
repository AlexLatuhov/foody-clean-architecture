package com.example.domain.usecase.implementations

import com.example.domain.gateway.RemoveFavoriteRecipeGateWay
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.usecase.interfaces.RemoveFavoriteRecipeUseCase
import javax.inject.Inject

class RemoveFavoriteRecipeUseCaseImpl @Inject constructor(
    private val removeFavoriteRecipeGateWay: RemoveFavoriteRecipeGateWay
) : RemoveFavoriteRecipeUseCase {

    override suspend fun removeFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain) =
        removeFavoriteRecipeGateWay.deleteFavoriteRecipe(*favoritesEntity)

}
