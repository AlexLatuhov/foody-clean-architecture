package com.example.foody.domain.recipes

import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.MealAndDietType
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway : MealAndDietTypeSaver {
    fun readMealAndDietType(): Flow<MealAndDietType>

    suspend fun getData(): Flow<DataRequestResult>
}