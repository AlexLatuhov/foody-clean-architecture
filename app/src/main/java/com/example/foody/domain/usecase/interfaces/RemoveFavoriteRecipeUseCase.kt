package com.example.foody.domain.usecase.interfaces

import com.example.foody.domain.models.FavoritesEntityDomain

interface RemoveFavoriteRecipeUseCase {

    suspend fun removeFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): Boolean

}