package com.example.foody.domain.usecase

import com.example.foody.domain.gateway.GetFoodJokeGateway
import javax.inject.Inject

class GetFoodJokeUseCase @Inject constructor(
    private val getFoodJokeGateway: GetFoodJokeGateway
) {
    suspend fun getData() = getFoodJokeGateway.getData()
}
