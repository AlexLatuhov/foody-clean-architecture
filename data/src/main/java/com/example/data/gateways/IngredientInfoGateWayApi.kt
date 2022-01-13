package com.example.data.gateways

import com.example.data.Constants
import com.example.data.api.FoodRecipesApi
import com.example.data.mappers.convertToDomainItem
import com.example.data.mappers.convertToLocalDbItem
import com.example.domain.gateway.IngredientInfoGateWay
import com.example.domain.models.ExtendedIngredientDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IngredientInfoGateWayApi @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) : IngredientInfoGateWay {

    private var requestId = ""

    private val ingredientRequestResult = flow {
        emit(requestIngredientInfo(requestId))
    }

    private suspend fun requestIngredientInfo(
        requestId: String
    ): ExtendedIngredientDomain {
        val response = foodRecipesApi.getIngredientInfo(requestId, Constants.API_KEY)
        val result = response.body()
        return result?.convertToLocalDbItem()?.convertToDomainItem()
            ?: ExtendedIngredientDomain(0.0, "", "", "", "", "")
    }

    override fun getIngredientInfo(id: String): Flow<ExtendedIngredientDomain> {
        requestId = id
        return ingredientRequestResult
    }
}