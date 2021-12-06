package com.example.foody.data.repositories

import com.example.foody.data.database.models.FoodJokeEntity

interface JokeStorage {

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity): Boolean

    suspend fun readFoodJoke(): List<FoodJokeEntity>
}