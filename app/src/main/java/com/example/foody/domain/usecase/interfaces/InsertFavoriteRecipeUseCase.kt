package com.example.foody.domain.usecase.interfaces

import com.example.foody.domain.models.FavoritesEntityDomain

interface InsertFavoriteRecipeUseCase {

    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain): Boolean

}