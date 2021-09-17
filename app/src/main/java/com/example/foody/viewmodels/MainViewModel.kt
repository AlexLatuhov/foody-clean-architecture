package com.example.foody.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import androidx.lifecycle.*
import com.example.foody.R
import com.example.foody.data.Repository
import com.example.foody.data.database.RecipesEntity
import com.example.foody.models.FoodRecipe
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

    val readRecipes: LiveData<List<RecipesEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertRecipes(recipesEntity: RecipesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRecipes(recipesEntity)
        }

    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = response.getRecipesResult()
                val foodRecipe = recipesResponse.value!!.data
                if (foodRecipe != null) {
                    insertRecipes(RecipesEntity(foodRecipe))
                }
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error("Recipes not found")
            }
        } else {
            val app = getApplication<Application>()
            recipesResponse.value =
                NetworkResult.Error(app.getString(R.string.no_internet_connection))
        }
    }

    private fun Response<FoodRecipe>.getRecipesResult(): NetworkResult<FoodRecipe> {
        when {
            message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            code() == 402 -> {
                return NetworkResult.Error("API Key Limited")
            }
            body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error("Recipes not found")
            }
            isSuccessful -> {
                return NetworkResult.Success(body()!!)
            }
            else -> {
                return NetworkResult.Error(message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
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