package com.example.foody.data.gateways

import com.example.foody.data.mappers.convertToDataBaseItem
import com.example.foody.data.repositories.FavoriteRecipesEditor
import com.example.foody.domain.gateway.DeleteAllFavoriteRecipeGateWay
import com.example.foody.domain.gateway.InsertFavoriteRecipeGateWay
import com.example.foody.domain.gateway.RemoveFavoriteRecipeGateWay
import com.example.foody.domain.models.FavoritesEntityDomain
import javax.inject.Inject

class DataFavoriteRecipesEditorGateway @Inject constructor(
    private val favoriteRecipesEditor: FavoriteRecipesEditor
) : InsertFavoriteRecipeGateWay, RemoveFavoriteRecipeGateWay, DeleteAllFavoriteRecipeGateWay {

    override suspend fun insert(favoritesEntity: FavoritesEntityDomain) =
        favoriteRecipesEditor.insertFavoriteRecipes(favoritesEntity.convertToDataBaseItem())

    override suspend fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain) =
        favoriteRecipesEditor.deleteFavoriteRecipe(
            *favoritesEntity.map { it.convertToDataBaseItem() }.toTypedArray()
        )

    override suspend fun deleteAll() = favoriteRecipesEditor.deleteAll()
}