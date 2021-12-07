package com.example.domain.gateway

import com.example.domain.models.request.FavOperationResult

interface DeleteAllFavoriteRecipeGateWay {

    suspend fun deleteAll(): FavOperationResult

}