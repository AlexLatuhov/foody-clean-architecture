package com.example.data.repositories

import com.example.data.database.models.FoodJokeEntity

interface JokeStorage {

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity): Boolean//todo use custom object

    suspend fun readFoodJoke(): List<FoodJokeEntity>
}