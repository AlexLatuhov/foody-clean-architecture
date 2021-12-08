package com.example.domain.gateway

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult

interface InsertFavoriteRecipeGateWay {

    suspend fun insert(favoritesEntity: FavoritesEntityDomain): OperationResult

}