package com.example.data.repositories

import com.example.data.database.models.FavoritesEntity
import com.example.domain.models.request.OperationResult

interface FavoriteRecipesEditor {

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity): OperationResult

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntity): OperationResult

    suspend fun deleteAll(): OperationResult
}