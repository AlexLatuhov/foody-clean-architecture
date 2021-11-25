package com.example.foody.domain.usecase

import javax.inject.Inject

class ReadFavoriteRecipesUseCase @Inject constructor(
    private val readFavoriteRecipesGateWay: ReadFavoriteRecipesGateWay
) {
    fun readFavoriteRecipes() = readFavoriteRecipesGateWay.readFavoriteRecipes()
}
