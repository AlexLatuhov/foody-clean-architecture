package com.example.foody.domain.usecase

import com.example.foody.domain.datamanage.MealAndDietType
import com.example.foody.domain.models.DataRequestResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RequestRecipesUseCase @Inject constructor(
    private val requestRecipesGateway: RequestRecipesGateway
) {
    suspend fun getData(): Flow<DataRequestResult> {
        return requestRecipesGateway.getData()
    }

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        requestRecipesGateway.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun readMealAndDietType(): Flow<MealAndDietType> {
        return requestRecipesGateway.readMealAndDietType()
    }
}
