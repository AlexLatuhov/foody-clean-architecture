package com.example.foody.domain.gateway

import com.example.foody.data.repositories.MealAndDietTypeSaver
import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.models.MealAndDietTypeDomain
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway : MealAndDietTypeSaver {

    fun readMealAndDietType(): Flow<MealAndDietTypeDomain>

    suspend fun getData(): Flow<DataRequestResult>

}