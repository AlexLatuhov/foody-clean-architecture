package com.example.data.database.repositories

import com.example.data.Constants.Companion.DEFAULT_ID
import com.example.data.database.RecipesDao
import com.example.data.database.models.FoodJokeEntity
import com.example.data.repositories.JokeStorage
import com.example.domain.models.request.OperationResult
import javax.inject.Inject

class LocalFoodJokeStorage @Inject constructor(
    private val recipesDao: RecipesDao
) : JokeStorage {

    override suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) =
        if (recipesDao.insertFoodJoke(foodJokeEntity)
                .compareTo(DEFAULT_ID) == 0
        ) OperationResult.Success() else OperationResult.Fail

    override suspend fun readFoodJoke() = recipesDao.readFoodJoke()
}