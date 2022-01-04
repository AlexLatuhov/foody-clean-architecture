package com.example.presentation.favorites

import androidx.lifecycle.MutableLiveData
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult
import com.example.domain.usecase.implementations.RemoveFavoriteRecipeUseCaseImpl
import com.example.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.InsertFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectableFavoritesViewModel @Inject constructor(
    readFavoriteRecipesUseCase: ReadFavoriteRecipesUseCase,
    insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
    removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCaseImpl,
    deleteAllFavoriteRecipeUseCase: DeleteAllFavoriteRecipeUseCase
) : FavoritesViewModel(
    readFavoriteRecipesUseCase,
    insertFavoriteRecipeUseCase,
    removeFavoriteRecipeUseCase,
    deleteAllFavoriteRecipeUseCase
) {
    var multiSelection = false
    val screenState = MutableLiveData<ScreenState>()
    private var selectedRecipes = arrayListOf<FavoritesEntityDomain>()

    fun isSelected(currentRecipe: FavoritesEntityDomain) = selectedRecipes.contains(currentRecipe)

    fun deleteSelected() = deleteFavoriteRecipe(*selectedRecipes.toTypedArray())

    fun switchToMultiSelection(currentRecipe: FavoritesEntityDomain) {
        if (!multiSelection) {
            multiSelection = true
            applySelectionToItem(currentRecipe)
            screenState.postValue(ScreenState.StartAction)
        }
    }

    fun selectedItemsSize() = selectedRecipes.size

    private fun applySelectionToItem(currentRecipe: FavoritesEntityDomain) {
        if (isSelected(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
        } else {
            selectedRecipes.add(currentRecipe)
        }
    }

    fun applySelection(currentRecipe: FavoritesEntityDomain) {
        applySelectionToItem(currentRecipe)
        if (selectedRecipes.isEmpty()) {
            multiSelection = false
            screenState.postValue(ScreenState.ClearMode)
        } else {
            screenState.postValue(ScreenState.ApplyTitle)
        }
    }

    override fun postOperationResult(
        useCaseRes: OperationResult,
        successOperationResult: OperationResult
    ) {
        super.postOperationResult(useCaseRes, successOperationResult)
        onActionEnded()
        screenState.postValue(ScreenState.ClearMode)
    }

    fun onActionEnded() {
        multiSelection = false
        selectedRecipes.clear()
    }

    sealed class ScreenState {
        object StartAction : ScreenState()
        object ClearMode : ScreenState()
        object ApplyTitle : ScreenState()
    }
}