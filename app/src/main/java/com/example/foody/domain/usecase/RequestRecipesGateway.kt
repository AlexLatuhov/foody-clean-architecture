package com.example.foody.domain.usecase

import com.example.foody.domain.datamanage.MealAndDietType
import com.example.foody.domain.datamanage.MealAndDietTypeSaver
import com.example.foody.domain.models.DataRequestResult
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway : MealAndDietTypeSaver {
    fun readMealAndDietType(): Flow<MealAndDietType>

    suspend fun getData(): Flow<DataRequestResult>
}