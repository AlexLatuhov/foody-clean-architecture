package com.example.domain.usecase.interfaces

import com.example.domain.models.MealAndDietTypeDomain
import com.example.domain.models.request.RecipesDataRequestResult
import kotlinx.coroutines.flow.Flow

interface RecipesDataInteractor {

    fun obtainRecipesData(): Flow<RecipesDataRequestResult>

    fun readMealAndDietType(): Flow<MealAndDietTypeDomain>

    suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )
}