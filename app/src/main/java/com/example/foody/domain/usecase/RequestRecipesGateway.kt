package com.example.foody.domain.usecase

import com.example.foody.data.database.models.DataRequestResult
import com.example.foody.data.database.repositories.MealAndDietType
import com.example.foody.domain.repositories.MealAndDietTypeSaver
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway : MealAndDietTypeSaver {
    fun readMealAndDietType(): Flow<MealAndDietType>

    suspend fun getData(): Flow<DataRequestResult>
}