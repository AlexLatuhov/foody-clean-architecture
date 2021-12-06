package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.InsertFavoriteRecipeGateWay
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.usecase.interfaces.InsertFavoriteRecipeUseCase
import javax.inject.Inject

class InsertFavoriteRecipeUseCaseImpl @Inject constructor(
    private val insertFavoriteRecipeGateWay: InsertFavoriteRecipeGateWay
) : InsertFavoriteRecipeUseCase {

    override suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        insertFavoriteRecipeGateWay.insert(favoritesEntity)

}
