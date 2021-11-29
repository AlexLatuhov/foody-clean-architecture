package com.example.foody.domain.usecase

import com.example.foody.domain.models.FavoritesEntityDomain

interface InsertFavoriteRecipeGateWay {
    suspend fun insert(favoritesEntity: FavoritesEntityDomain): Boolean
}