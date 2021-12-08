package com.example.data.repositories

import com.example.data.database.repositories.MealAndDietType
import com.example.domain.gateway.MealAndDietTypeSaver
import com.example.domain.models.request.OperationResult
import kotlinx.coroutines.flow.Flow

interface MealAndDietRepository : MealAndDietTypeSaver {

    suspend fun saveMealAndDietType(): OperationResult

    fun readMealAndDietType(): Flow<MealAndDietType>

    suspend fun hasTempValue(): Boolean

    suspend fun selectedMealType(): String

    suspend fun selectedDietType(): String
}