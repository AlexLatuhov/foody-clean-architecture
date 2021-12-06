package com.example.domain.gateway

import com.example.domain.DataProviderRequestResult
import com.example.domain.models.FoodJokeDomain
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeGateway {

    suspend fun getData(): Flow<DataProviderRequestResult<FoodJokeDomain>>

}