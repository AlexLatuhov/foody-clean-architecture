package com.example.data.repositories

import com.example.data.database.repositories.MealAndDietType
import com.example.domain.gateway.MealAndDietTypeSaver
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository : MealAndDietTypeSaver {

    suspend fun saveMealAndDietType()

    val readMealAndDietType: Flow<MealAndDietType>

    fun hasTempValue(): Boolean

    suspend fun selectedMealType(): String

    suspend fun selectedDietType(): String
}