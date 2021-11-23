package com.example.foody.domain

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveMealAndDietType(
        mealAndDietType: MealAndDietType?
    )

    val readMealAndDietType: Flow<MealAndDietType>
}