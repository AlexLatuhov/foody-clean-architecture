package com.example.domain.gateway

import com.example.domain.models.request.OperationResult

interface DeleteAllFavoriteRecipeGateWay {

    suspend fun deleteAll(): OperationResult

}