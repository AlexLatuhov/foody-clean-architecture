package com.example.foody.data.repositories

import com.example.foody.data.database.models.FavoritesEntity

interface FavoriteRecipesEditor {

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity): Boolean

    suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntity): Boolean

    suspend fun deleteAll(): Boolean
}