package com.example.foody.data.gateways

import com.example.foody.data.database.models.Result
import com.example.foody.domain.repositories.RecipesLoader
import com.example.foody.domain.usecase.LoadRecipesGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataLoadRecipesGateway @Inject constructor(
    private val recipesLoader: RecipesLoader
) : LoadRecipesGateway {

    override fun loadDataFromCache(searchQuery: String?): Flow<List<Result>?> {
        return recipesLoader.readRecipes().map { database ->
            val resultsTemp = database.getOrNull(0)?.foodRecipe?.results
            if (searchQuery != null)
                resultsTemp?.filter { result ->
                    result.title.contains(
                        searchQuery,
                        true
                    )
                }
            else resultsTemp
        }
    }
}