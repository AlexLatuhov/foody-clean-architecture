package com.example.foody.data.gateways

import android.content.Context
import com.example.foody.R
import com.example.foody.data.Constants
import com.example.foody.data.api.RemoteDataSource
import com.example.foody.data.api.models.FoodJokeDataItem
import com.example.foody.data.database.models.FoodJokeEntity
import com.example.foody.data.hasInternetConnection
import com.example.foody.domain.LocalDbToDomainMapper
import com.example.foody.domain.NetworkResult
import com.example.foody.domain.gateway.GetFoodJokeGateway
import com.example.foody.domain.models.FoodJokeDomain
import com.example.foody.domain.repositories.JokeStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject

class RequestFoodJokeGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val remoteDataSource: RemoteDataSource,
    private val localDbToDomainMapper: LocalDbToDomainMapper,
    private val jokeStorage: JokeStorage
) : GetFoodJokeGateway {
    private val dataRequestResult =
        MutableStateFlow<NetworkResult<FoodJokeDomain>>(NetworkResult.Loading())

    override suspend fun getData(): Flow<NetworkResult<FoodJokeDomain>> {
        dataRequestResult.value = requestAndStoreData()
        return dataRequestResult
    }

    private suspend fun requestAndStoreData(): NetworkResult<FoodJokeDomain> {
        if (context.hasInternetConnection()) {
            try {
                val response = remoteDataSource.getFoodJoke(Constants.API_KEY)
                val res = response.getFoodJokeResult()
                val foodJoke = res.data
                if (foodJoke != null) {
                    val dbEntity = FoodJokeEntity(foodJoke)
                    val insertResult = jokeStorage.insertFoodJoke(dbEntity)
                    return if (insertResult)
                        NetworkResult.Success(localDbToDomainMapper.map(dbEntity))
                    else loadFromCache(context.getString(R.string.unknown_error))
                }
            } catch (e: Exception) {
                return loadFromCache("Not found")
            }
        }
        return loadFromCache(context.getString(R.string.no_internet_connection))
    }

    private suspend fun loadFromCache(errorMessage: String): NetworkResult.Error<FoodJokeDomain> {
        val entitiesList = jokeStorage.readFoodJoke()
        return if (entitiesList.isNullOrEmpty()) NetworkResult.Error(
            errorMessage,
            localDbToDomainMapper.map(entitiesList[0])
        ) else NetworkResult.Error(errorMessage)
    }

    private fun Response<FoodJokeDataItem>.getFoodJokeResult(): NetworkResult<FoodJokeDataItem> {//todo remove duplication
        return when {
            message().toString().contains("timeout") -> {
                NetworkResult.Error("Timeout")
            }
            code() == 402 -> {
                NetworkResult.Error("API Key Limited")
            }
            isSuccessful -> {
                NetworkResult.Success(body()!!)
            }
            else -> {
                NetworkResult.Error(message())
            }
        }
    }
}