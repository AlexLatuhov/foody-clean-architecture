package com.example.data.database.repositories

import com.example.data.Constants.Companion.DEFAULT_ID
import com.example.data.database.RecipesDao
import com.example.data.database.models.FoodJokeEntity
import com.example.data.repositories.JokeStorage
import javax.inject.Inject

class LocalFoodJokeStorage @Inject constructor(
    private val recipesDao: RecipesDao
) : JokeStorage {

    override suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) =
        recipesDao.insertFoodJoke(foodJokeEntity).compareTo(DEFAULT_ID) == 0

    override suspend fun readFoodJoke() = recipesDao.readFoodJoke()
}