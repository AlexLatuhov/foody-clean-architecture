package com.example.domain.gateway

import com.example.domain.models.MealAndDietTypeDomain
import com.example.domain.models.request.RecipesDataRequestResult
import kotlinx.coroutines.flow.Flow

interface RequestRecipesGateway : MealAndDietTypeSaver {

    fun readMealAndDietType(): Flow<MealAndDietTypeDomain>

    suspend fun obtainRecipesData(): Flow<RecipesDataRequestResult>

}