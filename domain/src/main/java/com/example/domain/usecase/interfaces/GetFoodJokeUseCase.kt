package com.example.domain.usecase.interfaces

import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.example.domain.models.request.InfoTypeDomain
import kotlinx.coroutines.flow.Flow

interface GetFoodJokeUseCase {

    fun obtainFoodJokeData(type: InfoTypeDomain): Flow<DataProviderRequestResult<FoodJokeDomain>>

}