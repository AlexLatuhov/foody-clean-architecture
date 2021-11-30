package com.example.foody.domain.gateway

import com.example.foody.domain.models.FavoritesEntityDomain
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteRecipesGateWay {
    fun readFavoriteRecipes(): Flow<List<FavoritesEntityDomain>>
}