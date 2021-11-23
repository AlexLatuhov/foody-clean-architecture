package com.example.foody.domain

import com.example.foody.domain.recipes.MealAndDietTypeSaver
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository : MealAndDietTypeSaver {
    suspend fun saveMealAndDietType()

    val readMealAndDietType: Flow<MealAndDietType>

    fun hasTempValue(): Boolean

    suspend fun selectedMealType(): String

    suspend fun selectedDietType(): String
}