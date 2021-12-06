package com.example.data.gateways

import com.example.data.mappers.convertToDomainItem
import com.example.data.repositories.RecipesLoader
import com.example.domain.gateway.LoadRecipesGateway
import com.example.domain.gateway.ReadFavoriteRecipesGateWay
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataLoadRecipeGateway @Inject constructor(
    private val recipesLoader: RecipesLoader
) : LoadRecipesGateway, ReadFavoriteRecipesGateWay {

    override fun loadDataFromCache(searchQuery: String?) =
        recipesLoader.readRecipes().map { database ->
            val resultsTemp = database.getOrNull(0)?.foodRecipeEntity?.recipeItemEntities
            val returnResult = if (searchQuery != null)
                resultsTemp?.filter { result ->
                    result.title.contains(
                        searchQuery,
                        true
                    )
                }
            else resultsTemp
            returnResult?.convertToDomainItem() ?: emptyList()
        }

    override fun readFavoriteRecipes() = recipesLoader.readFavoriteRecipes()
        .map { items -> items.map { favoritesEntity -> favoritesEntity.convertToDomainItem() } }
}