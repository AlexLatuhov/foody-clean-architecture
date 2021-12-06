package com.example.domain.usecase.implementations

import com.example.domain.gateway.InsertFavoriteRecipeGateWay
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.usecase.interfaces.InsertFavoriteRecipeUseCase
import javax.inject.Inject

class InsertFavoriteRecipeUseCaseImpl @Inject constructor(
    private val insertFavoriteRecipeGateWay: InsertFavoriteRecipeGateWay
) : InsertFavoriteRecipeUseCase {

    override suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        insertFavoriteRecipeGateWay.insert(favoritesEntity)

}
