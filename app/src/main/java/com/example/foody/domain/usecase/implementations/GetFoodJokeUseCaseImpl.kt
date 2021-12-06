package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.GetFoodJokeGateway
import com.example.foody.domain.usecase.interfaces.GetFoodJokeUseCase
import javax.inject.Inject

class GetFoodJokeUseCaseImpl @Inject constructor(
    private val getFoodJokeGateway: GetFoodJokeGateway
) : GetFoodJokeUseCase {

    override suspend fun getData() = getFoodJokeGateway.getData()

}
