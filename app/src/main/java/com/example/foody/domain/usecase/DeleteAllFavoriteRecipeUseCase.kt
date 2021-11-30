package com.example.foody.domain.usecase

import com.example.foody.domain.gateway.DeleteAllFavoriteRecipeGateWay
import javax.inject.Inject

class DeleteAllFavoriteRecipeUseCase @Inject constructor(
    private val deleteAllFavoriteRecipeGateWay: DeleteAllFavoriteRecipeGateWay
) {
    suspend fun deleteAll() =
        deleteAllFavoriteRecipeGateWay.deleteAll()
}
