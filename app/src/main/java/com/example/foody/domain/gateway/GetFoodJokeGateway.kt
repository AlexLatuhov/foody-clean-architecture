package com.example.foody.domain.gateway

import com.example.foody.domain.DataProviderRequestResult
import com.example.foody.domain.models.FoodJokeDomain
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeGateway {
    suspend fun getData(): Flow<DataProviderRequestResult<FoodJokeDomain>>
}