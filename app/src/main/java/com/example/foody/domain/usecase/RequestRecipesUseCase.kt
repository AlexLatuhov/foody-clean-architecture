package com.example.foody.domain.usecase

import com.example.foody.domain.gateway.RequestRecipesGateway
import javax.inject.Inject

class RequestRecipesUseCase @Inject constructor(
    private val requestRecipesGateway: RequestRecipesGateway
) {
    suspend fun getData() = requestRecipesGateway.getData()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) =
        requestRecipesGateway.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)

    fun readMealAndDietType() = requestRecipesGateway.readMealAndDietType()
}
