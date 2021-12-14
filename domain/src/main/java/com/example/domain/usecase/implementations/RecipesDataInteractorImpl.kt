package com.example.domain.usecase.implementations

import com.example.domain.gateway.RequestRecipesGateway
import com.example.domain.usecase.interfaces.RecipesDataInteractor
import javax.inject.Inject

class RecipesDataInteractorImpl @Inject constructor(
    private val requestRecipesGateway: RequestRecipesGateway
) : RecipesDataInteractor {

    override suspend fun obtainRecipesData() =
        requestRecipesGateway.obtainRecipesData()

    override suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) =
        requestRecipesGateway.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)

    override fun readMealAndDietType() = requestRecipesGateway.readMealAndDietType()
}
