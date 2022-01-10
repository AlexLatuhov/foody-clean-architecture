package com.example.foody

import com.example.domain.models.MealAndDietTypeDomain
import com.example.domain.models.request.RecipesDataRequestResult
import com.example.domain.usecase.interfaces.RecipesDataInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeRecipesDataInteractor @Inject constructor() : RecipesDataInteractor {
    override fun obtainRecipesData(): Flow<RecipesDataRequestResult> = flow {
        emit(RecipesDataRequestResult.ApiKetLimited)
    }

    override fun readMealAndDietType(): Flow<MealAndDietTypeDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        TODO("Not yet implemented")
    }
}