package com.example.foody.data.gateways

import com.example.foody.data.database.models.Recipe
import com.example.foody.domain.repositories.RecipesLoader
import com.example.foody.domain.usecase.LoadRecipesGateway
import com.example.foody.domain.usecase.ReadFavoriteRecipesGateWay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataLoadRecipesGateway @Inject constructor(
    private val recipesLoader: RecipesLoader
) : LoadRecipesGateway, ReadFavoriteRecipesGateWay {

    override fun loadDataFromCache(searchQuery: String?): Flow<List<Recipe>?> {
        return recipesLoader.readRecipes().map { database ->
            val resultsTemp = database.getOrNull(0)?.foodRecipe?.recipes
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

    override fun readFavoriteRecipes() = recipesLoader.readFavoriteRecipes()
}