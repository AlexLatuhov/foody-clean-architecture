package com.example.foody.domain.usecase.interfaces

import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.models.MealAndDietTypeDomain
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