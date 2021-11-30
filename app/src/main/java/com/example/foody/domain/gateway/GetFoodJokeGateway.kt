package com.example.foody.domain.gateway

import com.example.foody.domain.NetworkResult
import com.example.foody.domain.models.FoodJokeDomain
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeGateway {
    suspend fun getData(): Flow<NetworkResult<FoodJokeDomain>>
}