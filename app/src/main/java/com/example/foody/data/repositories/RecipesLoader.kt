package com.example.foody.data.repositories

import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.data.database.models.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesLoader {

    fun readRecipes(): Flow<List<RecipesEntity>>

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>
}