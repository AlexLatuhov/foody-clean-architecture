package com.example.domain.gateway

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult

interface RemoveFavoriteRecipeGateWay {

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain): OperationResult

}