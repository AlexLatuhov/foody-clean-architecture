package com.example.foody.domain.gateway

import com.example.foody.domain.models.FavoritesEntityDomain

interface RemoveFavoriteRecipeGateWay {

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): Boolean

}