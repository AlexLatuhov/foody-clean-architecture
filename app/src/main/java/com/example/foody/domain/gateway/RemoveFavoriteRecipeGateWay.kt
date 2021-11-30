package com.example.foody.domain.gateway

import com.example.foody.domain.models.FavoritesEntityDomain

interface RemoveFavoriteRecipeGateWay {
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntityDomain): Boolean
}