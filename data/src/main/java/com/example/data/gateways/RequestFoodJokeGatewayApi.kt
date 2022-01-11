package com.example.data.gateways

import android.content.Context
import com.example.data.Constants
import com.example.data.api.RecipesDataHandler
import com.example.data.database.models.FoodJokeEntity
import com.example.data.extentions.hasInternetConnection
import com.example.data.extentions.wasKeyLimited
import com.example.data.mappers.convertToDomain
import com.example.data.repositories.JokeStorage
import com.example.domain.gateway.GetFoodJokeGateway
import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.example.domain.models.request.InfoTypeDomain
import com.example.domain.models.request.OperationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import javax.inject.Inject

class RequestFoodJokeGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val recipesDataHandler: RecipesDataHandler,
    private val jokeStorage: JokeStorage
) : GetFoodJokeGateway {

    private var typeInfoTypeDomain = InfoTypeDomain.Joke

    private val dataRequestResult = flow {
        emit(requestAndStoreData(typeInfoTypeDomain))
    }

    override fun obtainFoodJokeData(type: InfoTypeDomain): Flow<DataProviderRequestResult<FoodJokeDomain>> {
        typeInfoTypeDomain = type
        return dataRequestResult
    }

    private suspend fun requestAndStoreData(type: InfoTypeDomain): DataProviderRequestResult<FoodJokeDomain> {
        if (context.hasInternetConnection()) {
            try {
                val pathStr = when (type) {
                    InfoTypeDomain.Joke -> "jokes/random"
                    else -> "trivia/random"
                }
                val response = recipesDataHandler.getFoodJoke(pathStr, Constants.API_KEY)
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
                return if (e is SocketTimeoutException)
                    DataProviderRequestResult.Timeout(loadFromCache())
                else
                    DataProviderRequestResult.UnknownError(loadFromCache())
            }
        }
        return DataProviderRequestResult.NoInternetError(loadFromCache())
    }

    private suspend fun loadFromCache(): FoodJokeDomain? {
        val entitiesList = jokeStorage.readFoodJoke()
        return if (entitiesList.isNullOrEmpty()) null else entitiesList[0].convertToDomain()
    }
}