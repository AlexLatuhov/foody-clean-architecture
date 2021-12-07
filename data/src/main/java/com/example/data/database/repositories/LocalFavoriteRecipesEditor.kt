package com.example.data.database.repositories

import com.example.data.database.RecipesDao
import com.example.data.database.models.FavoritesEntity
import com.example.data.repositories.FavoriteRecipesEditor
import com.example.domain.models.request.FavOperationResult
import javax.inject.Inject

class LocalFavoriteRecipesEditor @Inject constructor(
    private val recipesDao: RecipesDao
) : FavoriteRecipesEditor {

    private fun generateFavResult(operationResCount: Int): FavOperationResult =
        if (operationResCount > 0) FavOperationResult.Success() else FavOperationResult.Error

    override suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) =
        generateFavResult(recipesDao.insertFavoriteRecipe(favoritesEntity).toInt())

    override suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntity) =
        generateFavResult(recipesDao.deleteFavoriteRecipe(*favoritesEntity))

    override suspend fun deleteAll() = generateFavResult(recipesDao.deleteAll())
}