package com.example.foody.data.gateways

import android.content.Context
import android.util.Log
import com.example.foody.R
import com.example.foody.data.Constants
import com.example.foody.data.Constants.Companion.TEST_TAG
import com.example.foody.data.api.RemoteDataSource
import com.example.foody.data.api.models.FoodJokeDataItem
import com.example.foody.data.database.models.FoodJokeEntity
import com.example.foody.data.getErrorMessage
import com.example.foody.data.hasInternetConnection
import com.example.foody.domain.DataProviderRequestResult
import com.example.foody.domain.LocalDbToDomainMapper
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
        MutableStateFlow<DataProviderRequestResult<FoodJokeDomain>>(DataProviderRequestResult.Loading())

    override suspend fun getData(): Flow<DataProviderRequestResult<FoodJokeDomain>> {
        dataRequestResult.value = requestAndStoreData()
        return dataRequestResult
    }

    private suspend fun requestAndStoreData(): DataProviderRequestResult<FoodJokeDomain> {
        if (context.hasInternetConnection()) {
            try {
                val response = remoteDataSource.getFoodJoke(Constants.API_KEY)
                val res = response.getFoodJokeResult()
                val foodJoke = res.data
                if (foodJoke != null) {
                    val dbEntity = FoodJokeEntity(foodJoke)
                    val insertResult = jokeStorage.insertFoodJoke(dbEntity)
                    return if (insertResult)
                        DataProviderRequestResult.Success(localDbToDomainMapper.map(dbEntity))
                    else loadFromCache(context.getString(R.string.unknown_error))
                }
            } catch (e: Exception) {
                return loadFromCache("Not found")
            }
        }
        return loadFromCache(context.getString(R.string.no_internet_connection))
    }

    private suspend fun loadFromCache(errorMessage: String): DataProviderRequestResult.Error<FoodJokeDomain> {
        val entitiesList = jokeStorage.readFoodJoke()
        Log.d(TEST_TAG, "entitiesList $entitiesList")
        return if (entitiesList.isNullOrEmpty()) DataProviderRequestResult.Error(errorMessage) else DataProviderRequestResult.Error(
            errorMessage,
            localDbToDomainMapper.map(entitiesList[0])
        )
    }

    private fun Response<FoodJokeDataItem>.getFoodJokeResult(): DataProviderRequestResult<FoodJokeDataItem> {
        val error = getErrorMessage()
        return when {
            error != null -> {
                DataProviderRequestResult.Error(error)
            }
            else -> {
                DataProviderRequestResult.Success(body()!!)
            }
        }
    }
}