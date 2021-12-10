package com.example.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult
import com.example.domain.usecase.implementations.RemoveFavoriteRecipeUseCaseImpl
import com.example.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.InsertFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
import com.example.presentation.BaseViewModel
import com.example.presentation.SuccessAdd
import com.example.presentation.SuccessRemove
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    readFavoriteRecipesUseCase: ReadFavoriteRecipesUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCaseImpl,
    private val deleteAllFavoriteRecipeUseCase: DeleteAllFavoriteRecipeUseCase
) : BaseViewModel() {

    val readFavoriteRecipes = readFavoriteRecipesUseCase.readFavoriteRecipes().asLiveData()

    val favOperationResult = MutableLiveData<OperationResult>()

    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) = scopeLaunch {
        postOperationResult(
            insertFavoriteRecipeUseCase.insertFavoriteRecipe(favoritesEntity),
            SuccessAdd
        )
    }

    private fun postOperationResult(
        useCaseRes: OperationResult,
        successOperationResult: OperationResult
    ) {
        favOperationResult.postValue(if (useCaseRes is OperationResult.Success) successOperationResult else useCaseRes)
    }

    fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain) = scopeLaunch {
        postOperationResult(
            removeFavoriteRecipeUseCase.removeFavoriteRecipe(*favoritesEntity),
            SuccessRemove
        )
    }

    fun deleteAllFavoriteRecipes() = scopeLaunch {
        favOperationResult.postValue(deleteAllFavoriteRecipeUseCase.deleteAll())
    }
}