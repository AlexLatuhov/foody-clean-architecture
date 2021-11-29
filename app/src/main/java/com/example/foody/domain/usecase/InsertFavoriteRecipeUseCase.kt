package com.example.foody.domain.usecase

import com.example.foody.domain.models.FavoritesEntityDomain
import javax.inject.Inject

class InsertFavoriteRecipeUseCase @Inject constructor(
    private val insertFavoriteRecipeGateWay: InsertFavoriteRecipeGateWay
) {
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        insertFavoriteRecipeGateWay.insert(favoritesEntity)
}
