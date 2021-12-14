package com.example.data.gateways

import android.content.Context
import com.example.data.Constants
import com.example.data.api.RecipesDataHandler
import com.example.data.database.models.FoodJokeEntity
import com.example.data.extentions.hasInternetConnection
import com.example.data.extentions.wasKeyLimited
import com.example.data.extentions.wasTimeout
import com.example.data.mappers.convertToDomain
import com.example.data.repositories.JokeStorage
import com.example.domain.gateway.GetFoodJokeGateway
import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.example.domain.models.request.OperationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RequestFoodJokeGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val recipesDataHandler: RecipesDataHandler,
    private val jokeStorage: JokeStorage
) : GetFoodJokeGateway {

    private val dataRequestResult =
        MutableStateFlow<DataProviderRequestResult<FoodJokeDomain>>(DataProviderRequestResult.Loading())

    override suspend fun obtainFoodJokeData(): Flow<DataProviderRequestResult<FoodJokeDomain>> {
        dataRequestResult.value = requestAndStoreData()
        return dataRequestResult
    }

    private suspend fun requestAndStoreData(): DataProviderRequestResult<FoodJokeDomain> {
        if (context.hasInternetConnection()) {
            try {
                val response = recipesDataHandler.getFoodJoke(Constants.API_KEY)
                val foodJoke = response.body()
                if (response.isSuccessful && foodJoke != null) {
                    val dbEntity = FoodJokeEntity(foodJoke)
                    val insertResult =
                        jokeStorage.insertFoodJoke(dbEntity) is OperationResult.Success
                    return if (insertResult)
                        DataProviderRequestResult.Success(dbEntity.convertToDomain())
                    else DataProviderRequestResult.UnknownError(loadFromCache())
                } else {
                    return when {
                        response.wasTimeout() -> {
                            DataProviderRequestResult.Timeout(loadFromCache())
                        }
                        response.wasKeyLimited() -> {
                            DataProviderRequestResult.ApiKetLimited(loadFromCache())
                        }
                        else -> {
                            DataProviderRequestResult.ErrorWithMessage(
                                response.message(),
                                loadFromCache()
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                return DataProviderRequestResult.UnknownError(loadFromCache())
            }
        }
        return DataProviderRequestResult.NoInternetError(loadFromCache())
    }

    private suspend fun loadFromCache(): FoodJokeDomain? {
        val entitiesList = jokeStorage.readFoodJoke()
        return if (entitiesList.isNullOrEmpty()) null else entitiesList[0].convertToDomain()
    }
}