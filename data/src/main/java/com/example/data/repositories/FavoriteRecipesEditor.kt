package com.example.data.repositories

import com.example.data.database.models.FavoritesEntity
import com.example.domain.models.request.FavOperationResult

interface FavoriteRecipesEditor {

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity): FavOperationResult

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntity): FavOperationResult

    suspend fun deleteAll(): FavOperationResult
}