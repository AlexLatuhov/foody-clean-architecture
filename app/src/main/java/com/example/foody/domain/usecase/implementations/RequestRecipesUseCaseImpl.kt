package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.RequestRecipesGateway
import com.example.foody.domain.usecase.interfaces.RequestRecipesUseCase
import javax.inject.Inject

class RequestRecipesUseCaseImpl @Inject constructor(
    private val requestRecipesGateway: RequestRecipesGateway
) : RequestRecipesUseCase {

    override suspend fun getData() = requestRecipesGateway.getData()

    override fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) =
        requestRecipesGateway.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)

    override fun readMealAndDietType() = requestRecipesGateway.readMealAndDietType()
}
