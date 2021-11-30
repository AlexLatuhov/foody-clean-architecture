package com.example.foody.domain.usecase

import com.example.foody.domain.gateway.ReadFavoriteRecipesGateWay
import javax.inject.Inject

class ReadFavoriteRecipesUseCase @Inject constructor(
    private val readFavoriteRecipesGateWay: ReadFavoriteRecipesGateWay
) {
    fun readFavoriteRecipes() = readFavoriteRecipesGateWay.readFavoriteRecipes()
}
