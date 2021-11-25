package com.example.foody.domain.usecase

import com.example.foody.data.database.models.FavoritesEntity
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteRecipesGateWay {
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>
}