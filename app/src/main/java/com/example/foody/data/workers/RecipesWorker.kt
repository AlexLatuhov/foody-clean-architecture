package com.example.foody.data.workers

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.foody.R
import com.example.foody.data.LocalDataSource
import com.example.foody.data.RemoteDataSource
import com.example.foody.data.database.entities.RecipesEntity
import com.example.foody.models.FoodRecipe
import com.example.foody.util.Constants
import com.example.foody.util.Constants.Companion.ERROR_MESSAGE
import com.example.foody.util.Constants.Companion.SELECTED_DIET
import com.example.foody.util.Constants.Companion.SELECTED_MEAL
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.Response

@HiltWorker
class RecipesWorker @AssistedInject constructor(
    @Assisted val context: Context,
    @Assisted val workerParams: WorkerParameters,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        if (hasInternetConnection()) {
            try {
                val queries = applyQueries(
                    inputData.getString(SELECTED_MEAL) ?: "", inputData.getString(
                        SELECTED_DIET
                    ) ?: ""
                )
                val response = remoteDataSource.getRecipes(queries)
                Log.d(Constants.TEST_TAG, "getRecipes $response")
                val responseResult = response.getRecipesResult()
                if (responseResult is Result.Success) {
                    val foodRecipe = response.body()
                    if (foodRecipe != null && response.isSuccessful) {
                        val entity = RecipesEntity(foodRecipe)
                        Log.d(Constants.TEST_TAG, "insertRecipes ${entity.foodRecipe.results.size}")
                        localDataSource.insertRecipes(entity)
                    }
                    Log.d(Constants.TEST_TAG, "Result.success()!")
                }
                return responseResult
            } catch (e: Exception) {
                Log.d(Constants.TEST_TAG, "***exception***")
                e.printStackTrace()
                return createError("Recipes not found")
            }
        } else {
            return createError(
                applicationContext.getString(R.string.no_internet_connection)
            )
        }
    }

    private fun createError(message: String): Result {
        return Result.failure(
            Data.Builder().putString(
                ERROR_MESSAGE, message
            ).build()
        )
    }

    private fun Response<FoodRecipe>.getRecipesResult(): Result {
        when {
            message().toString().contains("timeout") -> {
                return createError("Timeout")
            }
            code() == 402 -> {
                return createError("API Key Limited")
            }
            body()!!.results.isNullOrEmpty() -> {
                return createError("Recipes not found")
            }
            isSuccessful -> {
                return Result.success()
            }
            else -> {
                return createError(message())
            }
        }
    }

    private fun applyQueries(
        selectedMealType: String,
        selectedDietType: String
    ): HashMap<String, String> {
        Log.d(
            Constants.TEST_TAG,
            "selectedMealType $selectedMealType, selectedDietType $selectedDietType"
        )
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
        queries[Constants.QUERY_TYPE] = selectedMealType
        queries[Constants.QUERY_DIET] = selectedDietType
        queries[Constants.QUERY_RECIPE_INFO] = "true"
        queries[Constants.QUERY_INGREDIENTS] = "true"
        return queries
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasActiveConnections()
    }

    private fun NetworkCapabilities.hasActiveConnections(): Boolean {
        return when {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
