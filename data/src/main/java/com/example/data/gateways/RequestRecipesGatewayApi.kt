package com.example.data.gateways

import android.content.Context
import android.util.Log
import com.example.data.Constants
import com.example.data.Constants.Companion.CLEAN_TAG
import com.example.data.R
import com.example.data.api.RecipesDataHandler
import com.example.data.extentions.hasInternetConnection
import com.example.data.mappers.convertToDomainItem
import com.example.data.repositories.MealAndDietRepository
import com.example.domain.DataRequestResult
import com.example.domain.gateway.RequestRecipesGateway
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RequestRecipesGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val mealAndDietRepository: MealAndDietRepository,
    private val recipesDataHandler: RecipesDataHandler
) : RequestRecipesGateway {

    private val dataRequestResult = MutableStateFlow<DataRequestResult>(DataRequestResult.None)

    private suspend fun saveMealAndDietType() = mealAndDietRepository.saveMealAndDietType()

    private suspend fun hasTempValue(): Boolean = mealAndDietRepository.hasTempValue()

    override suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        mealAndDietRepository.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    override fun readMealAndDietType() = mealAndDietRepository.readMealAndDietType().map {
        it.convertToDomainItem()
    }

    override suspend fun requestAndStoreRecipesData(): Flow<DataRequestResult> {
        dataRequestResult.value =
            requestAndStoreData(
                mealAndDietRepository.selectedMealType(),
                mealAndDietRepository.selectedDietType()
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
                val response = recipesDataHandler.getRecipes(queries)
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