package com.example.foody.domain.repositories

import com.example.foody.data.database.repositories.MealAndDietType
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository : MealAndDietTypeSaver {
    suspend fun saveMealAndDietType()

    val readMealAndDietType: Flow<MealAndDietType>

    fun hasTempValue(): Boolean

    suspend fun selectedMealType(): String

    suspend fun selectedDietType(): String
}