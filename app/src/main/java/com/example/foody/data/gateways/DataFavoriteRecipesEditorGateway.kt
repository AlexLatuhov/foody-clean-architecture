package com.example.foody.data.gateways

import com.example.foody.domain.DomainToLocalDbMapper
import com.example.foody.domain.gateway.DeleteAllFavoriteRecipeGateWay
import com.example.foody.domain.gateway.InsertFavoriteRecipeGateWay
import com.example.foody.domain.gateway.RemoveFavoriteRecipeGateWay
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.repositories.FavoriteRecipesEditor
import javax.inject.Inject

class DataFavoriteRecipesEditorGateway @Inject constructor(
    private val domainToLocalDbMapper: DomainToLocalDbMapper,
    private val favoriteRecipesEditor: FavoriteRecipesEditor
) : InsertFavoriteRecipeGateWay, RemoveFavoriteRecipeGateWay, DeleteAllFavoriteRecipeGateWay {

    override suspend fun insert(favoritesEntity: FavoritesEntityDomain) =
        favoriteRecipesEditor.insertFavoriteRecipes(domainToLocalDbMapper.map(favoritesEntity))

    override suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        favoriteRecipesEditor.deleteFavoriteRecipe(domainToLocalDbMapper.map(favoritesEntity))

    override suspend fun deleteAll() = favoriteRecipesEditor.deleteAll()
}