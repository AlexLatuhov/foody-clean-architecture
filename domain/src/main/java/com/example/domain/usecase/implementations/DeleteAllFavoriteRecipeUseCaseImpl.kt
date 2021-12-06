package com.example.domain.usecase.implementations

import com.example.domain.gateway.DeleteAllFavoriteRecipeGateWay
import com.example.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase
import javax.inject.Inject

class DeleteAllFavoriteRecipeUseCaseImpl @Inject constructor(
    private val deleteAllFavoriteRecipeGateWay: DeleteAllFavoriteRecipeGateWay
) : DeleteAllFavoriteRecipeUseCase {

    override suspend fun deleteAll() =
        deleteAllFavoriteRecipeGateWay.deleteAll()

}
