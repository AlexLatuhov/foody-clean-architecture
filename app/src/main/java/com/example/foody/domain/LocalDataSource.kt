package com.example.foody.domain

import com.example.foody.data.database.RecipesDao
import com.example.foody.data.database.entities.FavoritesEntity
import com.example.foody.data.database.entities.FoodJokeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readFoodJoke(): Flow<List<FoodJokeEntity>> {
        return recipesDao.readFoodJoke()
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAll()
    }

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) {
        recipesDao.insertFoodJoke(foodJokeEntity)
    }
}