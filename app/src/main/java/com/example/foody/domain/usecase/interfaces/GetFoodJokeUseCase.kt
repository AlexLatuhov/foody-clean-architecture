package com.example.foody.domain.usecase.interfaces

import com.example.foody.domain.DataProviderRequestResult
import com.example.foody.domain.models.FoodJokeDomain
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeUseCase {

    suspend fun getData(): Flow<DataProviderRequestResult<FoodJokeDomain>>

}