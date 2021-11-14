package com.example.foody.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import androidx.lifecycle.*
import androidx.work.*
import com.example.foody.R
import com.example.foody.data.Repository
import com.example.foody.data.database.entities.FavoritesEntity
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.data.database.entities.RecipesEntity
import com.example.foody.data.workers.RecipesWorker
import com.example.foody.models.FoodJoke
import com.example.foody.util.Constants.Companion.RECIPES
import com.example.foody.util.Constants.Companion.SELECTED_DIET
import com.example.foody.util.Constants.Companion.SELECTED_MEAL
import com.example.foody.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val readFavoriteRecipes: LiveData<List<FavoritesEntity>> =
        repository.local.readFavoriteRecipes().asLiveData()
    val readRecipes: LiveData<List<RecipesEntity>> = repository.local.readRecipes().asLiveData()
    val readFoodJoke: LiveData<List<FoodJokeEntity>> = repository.local.readFoodJoke().asLiveData()

    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFavoriteRecipes(favoritesEntity)
        }

    fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteFavoriteRecipe(favoritesEntity)
        }

    private fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFoodJoke(foodJokeEntity)
        }
    }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteAllFavoriteRecipes()
        }

    var foodJokeResponse: MutableLiveData<NetworkResult<FoodJoke>> = MutableLiveData()

    fun getFoodJoke(apiKey: String) = viewModelScope.launch {
        getFoodJokeSafeCall(apiKey)
    }

    private suspend fun getFoodJokeSafeCall(apiKey: String) {
        foodJokeResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
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

    fun getRecipes(
        selectedMealType: String,
        selectedDietType: String
    ): LiveData<WorkInfo> {
        val data = Data.Builder().putString(SELECTED_MEAL, selectedMealType)
            .putString(SELECTED_DIET, selectedDietType).build()
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<RecipesWorker>().addTag(RECIPES).setInputData(data).build()
        val workManager = WorkManager.getInstance(getApplication())
        workManager.cancelUniqueWork(RECIPES)
        workManager.enqueue(uploadWorkRequest)
        return workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id)
    }

    private fun hasInternetConnection(): Boolean {//todo remove/move it
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasActiveConnections()
    }

    private fun NetworkCapabilities.hasActiveConnections(): Boolean {
        return when {
            hasTransport(TRANSPORT_WIFI) -> true
            hasTransport(TRANSPORT_CELLULAR) -> true
            hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}