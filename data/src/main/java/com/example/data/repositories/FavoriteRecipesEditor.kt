package com.example.data.repositories

import com.example.data.database.models.FavoritesEntity

interface FavoriteRecipesEditor {

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity): Boolean

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntity): Boolean

    suspend fun deleteAll(): Boolean
}