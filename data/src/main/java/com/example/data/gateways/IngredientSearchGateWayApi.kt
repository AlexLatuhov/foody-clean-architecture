package com.example.data.gateways

import com.example.data.Constants
import com.example.data.api.FoodRecipesApi
import com.example.data.mappers.convertToDomainItem
import com.example.domain.gateway.IngredientSearchGateWay
import com.example.domain.models.IngredientSearchDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IngredientSearchGateWayApi @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) : IngredientSearchGateWay {

    private var searchQuery = ""

    private val dataRequestResult = flow {
        emit(requestData(searchQuery))
    }

    private suspend fun requestData(
        searchQuery: String
    ): IngredientSearchDomain {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_SEARCH] = searchQuery
        queries[Constants.QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
        queries[Constants.QUERY_SORT] = "calories"
        queries[Constants.QUERY_SORT_DIR] = "desc"
        val response = foodRecipesApi.ingredientSearch(queries)
        val result = response.body()
        return IngredientSearchDomain(result?.results?.map {
            it.convertToDomainItem()
        } ?: emptyList())
    }

    override fun getIngredientsSearchResult(searchQuery: String): Flow<IngredientSearchDomain> {
        this.searchQuery = searchQuery
        return dataRequestResult
    }
}