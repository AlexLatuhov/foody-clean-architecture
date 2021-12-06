package com.example.data.repositories

import com.example.data.database.models.FoodJokeEntity

interface JokeStorage {

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity): Boolean

    suspend fun readFoodJoke(): List<FoodJokeEntity>
}