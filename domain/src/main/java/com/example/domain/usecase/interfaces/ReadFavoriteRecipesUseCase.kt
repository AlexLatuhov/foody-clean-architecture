package com.example.domain.usecase.interfaces

import com.example.domain.models.FavoritesEntityDomain
import kotlinx.coroutines.flow.Flow

interface ReadFavoriteRecipesUseCase {

    fun readFavoriteRecipes(): Flow<List<FavoritesEntityDomain>>

}