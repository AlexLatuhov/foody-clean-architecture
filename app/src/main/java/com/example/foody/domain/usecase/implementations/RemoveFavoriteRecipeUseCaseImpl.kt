package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.RemoveFavoriteRecipeGateWay
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.usecase.interfaces.RemoveFavoriteRecipeUseCase
import javax.inject.Inject

class RemoveFavoriteRecipeUseCaseImpl @Inject constructor(
    private val removeFavoriteRecipeGateWay: RemoveFavoriteRecipeGateWay
) : RemoveFavoriteRecipeUseCase {

    override suspend fun removeFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain) =
        removeFavoriteRecipeGateWay.deleteFavoriteRecipe(*favoritesEntity)

}
