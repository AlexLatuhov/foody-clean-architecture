package com.example.domain.usecase.implementations

import com.example.domain.gateway.IngredientSearchGateWay
import com.example.domain.usecase.interfaces.IngredientSearchUseCase
import javax.inject.Inject

class IngredientSearchUseCaseImpl @Inject constructor(
    private val ingredientSearchGateWay: IngredientSearchGateWay
) : IngredientSearchUseCase {
    override fun getIngredientsSearchResult(searchQuery: String) =
        ingredientSearchGateWay.getIngredientsSearchResult(searchQuery)
}