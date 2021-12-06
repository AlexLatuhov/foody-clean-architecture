package com.example.foody.domain.usecase.implementations

import com.example.foody.domain.gateway.DeleteAllFavoriteRecipeGateWay
import com.example.foody.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase
import javax.inject.Inject

class DeleteAllFavoriteRecipeUseCaseImpl @Inject constructor(
    private val deleteAllFavoriteRecipeGateWay: DeleteAllFavoriteRecipeGateWay
) : DeleteAllFavoriteRecipeUseCase {

    override suspend fun deleteAll() =
        deleteAllFavoriteRecipeGateWay.deleteAll()

}
