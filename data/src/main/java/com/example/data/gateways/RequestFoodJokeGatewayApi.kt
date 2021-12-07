package com.example.data.gateways

import android.content.Context
import com.example.data.Constants
import com.example.data.R
import com.example.data.api.RecipesDataHandler
import com.example.data.api.models.FoodJokeDataItem
import com.example.data.database.models.FoodJokeEntity
import com.example.data.extentions.getErrorMessage
import com.example.data.extentions.hasInternetConnection
import com.example.data.mappers.convertToDomain
import com.example.data.repositories.JokeStorage
import com.example.domain.DataProviderRequestResult
import com.example.domain.gateway.GetFoodJokeGateway
import com.example.domain.models.FoodJokeDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Response
import javax.inject.Inject

class RequestFoodJokeGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val recipesDataHandler: RecipesDataHandler,
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
                val response = recipesDataHandler.getFoodJoke(Constants.API_KEY)
                val res = response.getFoodJokeResult()
                val foodJoke = res.data
                if (foodJoke != null) {
                    val dbEntity = FoodJokeEntity(foodJoke)
                    val insertResult = jokeStorage.insertFoodJoke(dbEntity)
                    return if (insertResult)
                        DataProviderRequestResult.Success(dbEntity.convertToDomain())
                    else loadFromCache(context.getString(R.string.unknown_error))
                }
            } catch (e: Exception) {
                return loadFromCache(context.getString(R.string.not_found))
            }
        }
        return loadFromCache(context.getString(R.string.no_internet_connection))
    }

    private suspend fun loadFromCache(errorMessage: String): DataProviderRequestResult.Error<FoodJokeDomain> {
        val entitiesList = jokeStorage.readFoodJoke()
        return if (entitiesList.isNullOrEmpty()) DataProviderRequestResult.Error(errorMessage) else DataProviderRequestResult.Error(
            errorMessage,
            entitiesList[0].convertToDomain()
        )
    }

    private fun Response<FoodJokeDataItem>.getFoodJokeResult(): DataProviderRequestResult<FoodJokeDataItem> {
        val error = getErrorMessage(context)
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