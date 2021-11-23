package com.example.foody.domain.recipes

import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.MealAndDietType
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway {
    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )

    fun readMealAndDietType(): Flow<MealAndDietType>

    suspend fun getData(): Flow<DataRequestResult>
}