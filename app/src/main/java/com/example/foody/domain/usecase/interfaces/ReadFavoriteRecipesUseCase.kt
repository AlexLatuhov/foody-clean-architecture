package com.example.foody.domain.usecase.interfaces

import com.example.foody.domain.models.FavoritesEntityDomain
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteRecipesUseCase {

    fun readFavoriteRecipes(): Flow<List<FavoritesEntityDomain>>

}