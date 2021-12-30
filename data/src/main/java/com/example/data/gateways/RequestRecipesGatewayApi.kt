package com.example.data.gateways

import android.content.Context
import android.util.Log
import com.example.data.Constants
import com.example.data.Constants.CLEAN_TAG
import com.example.data.api.RecipesDataHandler
import com.example.data.extentions.hasInternetConnection
import com.example.data.mappers.convertToDomainItem
import com.example.data.repositories.MealAndDietRepository
import com.example.domain.gateway.RequestRecipesGateway
import com.example.domain.models.request.RecipesDataRequestResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.net.SocketTimeoutException
import javax.inject.Inject

class RequestRecipesGatewayApi @Inject constructor(
    @ApplicationContext val context: Context,
    private val mealAndDietRepository: MealAndDietRepository,
    private val recipesDataHandler: RecipesDataHandler
) : RequestRecipesGateway {

    private val dataRequestResult = flow {
        emit(
            requestAndStoreData(
                mealAndDietRepository.selectedMealType(),
                mealAndDietRepository.selectedDietType()
            )
        )
    }

    private suspend fun saveMealAndDietType() = mealAndDietRepository.saveMealAndDietType()

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

    override fun obtainRecipesData() = dataRequestResult

    private suspend fun requestAndStoreData(
        selectedMealType: String,
        selectedDietType: String
    ): RecipesDataRequestResult {
        if (context.hasInternetConnection()) {
            return try {
                val queries = applyQueries(
                    selectedMealType, selectedDietType
                )
                val response = recipesDataHandler.getRecipes(queries)
                if (response is RecipesDataRequestResult.Success) {
                    saveMealAndDietType()
                }
                response
            } catch (e: Exception) {
                Log.d(CLEAN_TAG, "Exception!")
                e.printStackTrace()
                if (e is SocketTimeoutException) RecipesDataRequestResult.Timeout else RecipesDataRequestResult.NotFound
            }
        } else {
            return RecipesDataRequestResult.NoConnectionError
        }
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