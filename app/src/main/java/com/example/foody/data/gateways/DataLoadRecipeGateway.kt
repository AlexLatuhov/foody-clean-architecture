package com.example.foody.data.gateways

import com.example.foody.domain.LocalDbToDomainMapper
import com.example.foody.domain.gateway.LoadRecipesGateway
import com.example.foody.domain.gateway.ReadFavoriteRecipesGateWay
import com.example.foody.domain.repositories.RecipesLoader
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataLoadRecipeGateway @Inject constructor(
    private val localDbToDomainMapper: LocalDbToDomainMapper,
    private val recipesLoader: RecipesLoader
) : LoadRecipesGateway, ReadFavoriteRecipesGateWay {

    override fun loadDataFromCache(searchQuery: String?) =
        recipesLoader.readRecipes().map { database ->
            val resultsTemp = database.getOrNull(0)?.foodRecipe?.recipes
            val returnResult = if (searchQuery != null)
                resultsTemp?.filter { result ->
                    result.title.contains(
                        searchQuery,
                        true
                    )
                }
            else resultsTemp
            localDbToDomainMapper.map(returnResult ?: emptyList())

        }

    override fun readFavoriteRecipes() = recipesLoader.readFavoriteRecipes()
        .map { items -> items.map { localDbToDomainMapper.map(it) } }
}