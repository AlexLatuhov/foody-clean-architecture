package com.example.domain.gateway

import com.example.domain.models.ExtendedIngredientDomain
import kotlinx.coroutines.flow.Flow

interface IngredientInfoGateWay {
    fun getIngredientInfo(id: String): Flow<ExtendedIngredientDomain>
}