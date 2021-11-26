package com.example.foody.domain.usecase

import com.example.foody.domain.models.FavoritesEntityDomain
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteRecipesGateWay {
    fun readFavoriteRecipes(): Flow<List<FavoritesEntityDomain>>
}