package com.example.domain.gateway

import com.example.domain.models.FavoritesEntityDomain

interface RemoveFavoriteRecipeGateWay {

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): Boolean

}