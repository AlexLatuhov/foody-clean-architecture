package com.example.domain.gateway

interface DeleteAllFavoriteRecipeGateWay {

    suspend fun deleteAll(): Boolean

}