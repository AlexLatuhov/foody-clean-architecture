package com.example.data.repositories

import com.example.data.database.models.FoodJokeEntity
import com.example.domain.models.request.OperationResult

interface JokeStorage {

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity): OperationResult

    suspend fun readFoodJoke(): List<FoodJokeEntity>
}