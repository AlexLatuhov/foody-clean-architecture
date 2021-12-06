package com.example.domain.usecase.interfaces

interface DeleteAllFavoriteRecipeUseCase {

    suspend fun deleteAll(): Boolean

}