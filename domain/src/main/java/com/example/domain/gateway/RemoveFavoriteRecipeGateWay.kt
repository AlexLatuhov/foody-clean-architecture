package com.example.domain.gateway

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.FavOperationResult

interface RemoveFavoriteRecipeGateWay {

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): FavOperationResult

}