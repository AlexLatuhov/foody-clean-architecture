package com.example.domain.usecase.interfaces

import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeUseCase {

    suspend fun obtainFoodJokeData(): Flow<DataProviderRequestResult<FoodJokeDomain>>

}