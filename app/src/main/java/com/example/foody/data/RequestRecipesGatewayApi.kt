package com.example.foody.data

import android.content.Context
import android.util.Log
import com.example.foody.R
import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.DataStoreRepository
import com.example.foody.domain.MealAndDietType
import com.example.foody.domain.recipes.RequestRecipesGateway
import com.example.foody.presentation.util.Constants
import com.example.foody.presentation.util.Constants.Companion.CLEAN_TAG
import com.example.foody.presentation.util.hasInternetConnection
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class RequestRecipesGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val dataStoreRepository: DataStoreRepository,
    private val remoteDataSource: RemoteDataSource
) : RequestRecipesGateway {

    private val dataRequestResult = MutableStateFlow<DataRequestResult>(DataRequestResult.None)

    private suspend fun saveMealAndDietType() = dataStoreRepository.saveMealAndDietType()
    private fun hasTempValue(): Boolean = dataStoreRepository.hasTempValue()

    override fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStoreRepository.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    override fun readMealAndDietType(): Flow<MealAndDietType> {
        return dataStoreRepository.readMealAndDietType
    }

    override suspend fun getData(): Flow<DataRequestResult> {
        dataRequestResult.value =
            requestAndStoreData(
                dataStoreRepository.selectedMealType(),
                dataStoreRepository.selectedDietType()
            )
        return dataRequestResult
    }

    private suspend fun requestAndStoreData(
        selectedMealType: String,
        selectedDietType: String
    ): DataRequestResult {
        if (context.hasInternetConnection()) {
            return try {
                val queries = applyQueries(
                    selectedMealType, selectedDietType
                )
                val response = remoteDataSource.getRecipes(queries)
                if (response is DataRequestResult.Error) {
                    createError(response.message)
                } else {
                    if (hasTempValue()) {
                        saveMealAndDietType()
                    }
                    DataRequestResult.Success
                }
            } catch (e: Exception) {
                Log.d(CLEAN_TAG, "Exception!")
                e.printStackTrace()
                createError(context.getString(R.string.recipes_not_found))
            }
        } else {
            return createError(
                context.getString(R.string.no_internet_connection)
            )
        }
    }

    private fun createError(message: String): DataRequestResult.Error {
        return DataRequestResult.Error(message)
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
}