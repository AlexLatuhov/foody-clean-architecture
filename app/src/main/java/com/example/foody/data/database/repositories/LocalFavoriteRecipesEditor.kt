package com.example.foody.data.database.repositories

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.domain.repositories.FavoriteRecipesEditor
import javax.inject.Inject

class LocalFavoriteRecipesEditor @Inject constructor(
    private val recipesDao: RecipesDao
) : FavoriteRecipesEditor {
    override suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) =
        recipesDao.insertFavoriteRecipe(favoritesEntity) > 0

    override suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntity) =
        recipesDao.deleteFavoriteRecipe(*favoritesEntity) > 0

    override suspend fun deleteAll() = recipesDao.deleteAll() > 0
}