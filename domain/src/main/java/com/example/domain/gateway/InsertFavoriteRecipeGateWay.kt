package com.example.domain.gateway

import com.example.domain.models.FavoritesEntityDomain

interface InsertFavoriteRecipeGateWay {

    suspend fun insert(favoritesEntity: FavoritesEntityDomain): Boolean

}