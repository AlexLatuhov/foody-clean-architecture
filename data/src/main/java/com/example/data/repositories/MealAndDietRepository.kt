package com.example.data.repositories

import com.example.data.database.repositories.MealAndDietType
import com.example.domain.gateway.MealAndDietTypeSaver
import kotlinx.coroutines.flow.Flow

interface MealAndDietRepository : MealAndDietTypeSaver {

    suspend fun saveMealAndDietType()//todo add return type

    fun readMealAndDietType(): Flow<MealAndDietType>

    suspend fun hasTempValue(): Boolean

    suspend fun selectedMealType(): String

    suspend fun selectedDietType(): String
}