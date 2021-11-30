package com.example.foody.domain.gateway

import com.example.foody.domain.models.FavoritesEntityDomain

interface InsertFavoriteRecipeGateWay {
    suspend fun insert(favoritesEntity: FavoritesEntityDomain): Boolean
}