package com.example.foody.domain.usecase.interfaces

interface DeleteAllFavoriteRecipeUseCase {

    suspend fun deleteAll(): Boolean

}