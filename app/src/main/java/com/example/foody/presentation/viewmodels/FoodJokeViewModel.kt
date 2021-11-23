package com.example.foody.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.foody.R
import com.example.foody.domain.database.entities.FoodJokeEntity
import com.example.foody.domain.models.FoodJoke
import com.example.foody.presentation.Repository
import com.example.foody.presentation.util.NetworkResult
import com.example.foody.presentation.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {
    val readFoodJoke: LiveData<List<FoodJokeEntity>> = repository.local.readFoodJoke().asLiveData()
    var foodJokeResponse: MutableLiveData<NetworkResult<FoodJoke>> = MutableLiveData()

    fun getFoodJoke(apiKey: String) = viewModelScope.launch {
        getFoodJokeSafeCall(apiKey)
    }

    private suspend fun getFoodJokeSafeCall(apiKey: String) {
        foodJokeResponse.value = NetworkResult.Loading()
        if (getApplication<Application>().hasInternetConnection()) {
            try {
                val response = repository.remote.getFoodJoke(apiKey)
                foodJokeResponse.value = response.getFoodJokeResult()
                val foodJoke = foodJokeResponse.value!!.data
                if (foodJoke != null) {
                    insertFoodJoke(FoodJokeEntity(foodJoke))
                }
            } catch (e: Exception) {
                foodJokeResponse.value = NetworkResult.Error("Not found")
            }
        } else {
            val app = getApplication<Application>()
            foodJokeResponse.value =
                NetworkResult.Error(app.getString(R.string.no_internet_connection))
        }
    }

    private fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFoodJoke(foodJokeEntity)
        }
    }

    private fun Response<FoodJoke>.getFoodJokeResult(): NetworkResult<FoodJoke> {
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