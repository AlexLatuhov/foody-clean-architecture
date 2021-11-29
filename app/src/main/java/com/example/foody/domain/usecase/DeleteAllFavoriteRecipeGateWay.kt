package com.example.foody.domain.usecase

interface DeleteAllFavoriteRecipeGateWay {
    suspend fun deleteAll(): Boolean
}