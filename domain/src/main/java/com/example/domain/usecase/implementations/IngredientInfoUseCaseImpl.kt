package com.example.domain.usecase.implementations

import com.example.domain.gateway.IngredientInfoGateWay
import com.example.domain.usecase.interfaces.IngredientInfoUseCase
import javax.inject.Inject

class IngredientInfoUseCaseImpl @Inject constructor(
    private val ingredientInfoGateWay: IngredientInfoGateWay
) : IngredientInfoUseCase {
    override fun getIngredientInfo(id: String) = ingredientInfoGateWay.getIngredientInfo(id)
}