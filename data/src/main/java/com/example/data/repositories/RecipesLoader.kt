package com.example.data.repositories

import com.example.data.database.models.FavoritesEntity
import com.example.data.database.models.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesLoader {

    fun readRecipes(): Flow<List<RecipesEntity>>

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>
}