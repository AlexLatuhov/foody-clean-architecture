package com.example.domain.usecase.interfaces

import com.example.domain.DataRequestResult
import com.example.domain.models.MealAndDietTypeDomain
import kotlinx.coroutines.flow.Flow

interface RecipesDataInteractor {

    suspend fun requestAndStoreRecipesData(): Flow<DataRequestResult>

    fun readMealAndDietType(): Flow<MealAndDietTypeDomain>

    suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )
}