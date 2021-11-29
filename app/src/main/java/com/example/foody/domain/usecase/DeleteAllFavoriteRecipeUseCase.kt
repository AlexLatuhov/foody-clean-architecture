package com.example.foody.domain.usecase

import javax.inject.Inject

class DeleteAllFavoriteRecipeUseCase @Inject constructor(
    private val deleteAllFavoriteRecipeGateWay: DeleteAllFavoriteRecipeGateWay
) {
    suspend fun deleteAll() =
        deleteAllFavoriteRecipeGateWay.deleteAll()
}
