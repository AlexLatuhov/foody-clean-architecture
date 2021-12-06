package com.example.domain.gateway

import com.example.domain.DataRequestResult
import com.example.domain.models.MealAndDietTypeDomain
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway : MealAndDietTypeSaver {

    fun readMealAndDietType(): Flow<MealAndDietTypeDomain>

    suspend fun getData(): Flow<DataRequestResult>

}