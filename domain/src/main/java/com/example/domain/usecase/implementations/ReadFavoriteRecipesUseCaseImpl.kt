package com.example.domain.usecase.implementations

import com.example.domain.gateway.ReadFavoriteRecipesGateWay
import com.example.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
import javax.inject.Inject

class ReadFavoriteRecipesUseCaseImpl @Inject constructor(
    private val readFavoriteRecipesGateWay: ReadFavoriteRecipesGateWay
) : ReadFavoriteRecipesUseCase {

    override fun readFavoriteRecipes() = readFavoriteRecipesGateWay.readFavoriteRecipes()

}
