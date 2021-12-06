package com.example.domain.gateway

import com.example.domain.models.FavoritesEntityDomain
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteRecipesGateWay {

    fun readFavoriteRecipes(): Flow<List<FavoritesEntityDomain>>

}