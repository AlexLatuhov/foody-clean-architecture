package com.example.foody.domain.gateway

interface DeleteAllFavoriteRecipeGateWay {
    suspend fun deleteAll(): Boolean
}