package com.example.domain.usecase.interfaces

import com.example.domain.DataProviderRequestResult
import com.example.domain.models.FoodJokeDomain
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeUseCase {

    suspend fun getData(): Flow<DataProviderRequestResult<FoodJokeDomain>>

}