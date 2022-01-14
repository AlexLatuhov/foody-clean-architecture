package com.example.domain.usecase.interfaces

import com.example.domain.models.ExtendedIngredientDomain
import kotlinx.coroutines.flow.Flow

interface IngredientInfoUseCase {
    fun getIngredientInfo(id: String): Flow<ExtendedIngredientDomain>
}