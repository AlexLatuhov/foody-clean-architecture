package com.example.domain.usecase.interfaces

import com.example.domain.DataRequestResult
import com.example.domain.models.MealAndDietTypeDomain
import kotlinx.coroutines.flow.Flow

interface RequestRecipesUseCase {

    //todo correct and probably rename to interactor
    suspend fun getData(): Flow<DataRequestResult>

    fun readMealAndDietType(): Flow<MealAndDietTypeDomain>

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )
}