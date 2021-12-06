package com.example.domain.usecase.interfaces

import com.example.domain.models.FavoritesEntityDomain

interface InsertFavoriteRecipeUseCase {

    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain): Boolean

}