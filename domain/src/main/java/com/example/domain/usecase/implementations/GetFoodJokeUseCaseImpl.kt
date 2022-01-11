package com.example.domain.usecase.implementations

import com.example.domain.gateway.GetFoodJokeGateway
import com.example.domain.models.request.InfoTypeDomain
import com.example.domain.usecase.interfaces.GetFoodJokeUseCase
import javax.inject.Inject

class GetFoodJokeUseCaseImpl @Inject constructor(
    private val getFoodJokeGateway: GetFoodJokeGateway
) : GetFoodJokeUseCase {

    override fun obtainFoodJokeData(type: InfoTypeDomain) =
        getFoodJokeGateway.obtainFoodJokeData(type)

}
