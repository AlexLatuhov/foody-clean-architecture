package com.example.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.FavOperationResult
import com.example.domain.usecase.implementations.RemoveFavoriteRecipeUseCaseImpl
import com.example.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.InsertFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
import com.example.presentation.SuccessAdd
import com.example.presentation.SuccessRemove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    readFavoriteRecipesUseCase: ReadFavoriteRecipesUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCaseImpl,
    private val deleteAllFavoriteRecipeUseCase: DeleteAllFavoriteRecipeUseCase
) : ViewModel() {

    val readFavoriteRecipes = readFavoriteRecipesUseCase.readFavoriteRecipes().asLiveData()
    val favOperationResult = MutableLiveData<FavOperationResult>()

    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            val useCaseRes = insertFavoriteRecipeUseCase.insertFavoriteRecipe(favoritesEntity)
            favOperationResult.postValue(if (useCaseRes is FavOperationResult.Success) SuccessAdd else useCaseRes)
        }

    fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            val useCaseRes = removeFavoriteRecipeUseCase.removeFavoriteRecipe(*favoritesEntity)
            favOperationResult.postValue(if (useCaseRes is FavOperationResult.Success) SuccessRemove else useCaseRes)
        }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {
            favOperationResult.postValue(
                deleteAllFavoriteRecipeUseCase.deleteAll()
            )
        }
}