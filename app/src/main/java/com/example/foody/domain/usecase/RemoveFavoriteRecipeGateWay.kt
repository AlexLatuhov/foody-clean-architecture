package com.example.foody.domain.usecase

import com.example.foody.domain.models.FavoritesEntityDomain

interface RemoveFavoriteRecipeGateWay {
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntityDomain): Boolean
}