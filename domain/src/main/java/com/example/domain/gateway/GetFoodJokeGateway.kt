package com.example.domain.gateway

import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeGateway {

    suspend fun getData(): Flow<DataProviderRequestResult<FoodJokeDomain>>

}