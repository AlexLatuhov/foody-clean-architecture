package com.example.domain.usecase.interfaces

import com.example.domain.models.FavoritesEntityDomain

interface RemoveFavoriteRecipeUseCase {

    suspend fun removeFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): Boolean

}