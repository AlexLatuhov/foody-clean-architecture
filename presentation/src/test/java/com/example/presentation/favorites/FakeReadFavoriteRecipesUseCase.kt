package com.example.presentation.favorites

import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeReadFavoriteRecipesUseCase : ReadFavoriteRecipesUseCase {
    override fun readFavoriteRecipes(): Flow<List<FavoritesEntityDomain>> = flow {

    }
}