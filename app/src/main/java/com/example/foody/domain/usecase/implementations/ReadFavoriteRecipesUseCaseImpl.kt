package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.ReadFavoriteRecipesGateWay
import com.example.foody.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
import javax.inject.Inject

class ReadFavoriteRecipesUseCaseImpl @Inject constructor(
    private val readFavoriteRecipesGateWay: ReadFavoriteRecipesGateWay
) : ReadFavoriteRecipesUseCase {

    override fun readFavoriteRecipes() = readFavoriteRecipesGateWay.readFavoriteRecipes()

}
