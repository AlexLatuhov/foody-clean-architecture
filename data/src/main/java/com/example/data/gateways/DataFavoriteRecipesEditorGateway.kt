package com.example.data.gateways

import com.example.data.mappers.convertToDataBaseItem
import com.example.data.repositories.FavoriteRecipesEditor
import com.example.domain.gateway.DeleteAllFavoriteRecipeGateWay
import com.example.domain.gateway.InsertFavoriteRecipeGateWay
import com.example.domain.gateway.RemoveFavoriteRecipeGateWay
import com.example.domain.models.FavoritesEntityDomain
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