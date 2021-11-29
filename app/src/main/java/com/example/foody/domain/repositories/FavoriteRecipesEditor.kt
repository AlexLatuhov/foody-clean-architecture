package com.example.foody.domain.repositories

import com.example.foody.data.database.models.FavoritesEntity

interface FavoriteRecipesEditor {
    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity): Boolean
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity): Boolean
    suspend fun deleteAll(): Boolean
}