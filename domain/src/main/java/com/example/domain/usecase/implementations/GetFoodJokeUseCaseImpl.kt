package com.example.domain.usecase.implementations

import com.example.domain.gateway.GetFoodJokeGateway
import com.example.domain.usecase.interfaces.GetFoodJokeUseCase
import javax.inject.Inject

class GetFoodJokeUseCaseImpl @Inject constructor(
    private val getFoodJokeGateway: GetFoodJokeGateway
) : GetFoodJokeUseCase {

    override suspend fun obtainFoodJokeData() = getFoodJokeGateway.obtainFoodJokeData()

}
