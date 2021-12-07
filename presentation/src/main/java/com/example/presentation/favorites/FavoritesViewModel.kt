package com.example.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.usecase.implementations.RemoveFavoriteRecipeUseCaseImpl
import com.example.domain.usecase.interfaces.DeleteAllFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.InsertFavoriteRecipeUseCase
import com.example.domain.usecase.interfaces.ReadFavoriteRecipesUseCase
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
    var operationResult: MutableLiveData<Boolean> = MutableLiveData()

    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            operationResult.postValue(
                insertFavoriteRecipeUseCase.insertFavoriteRecipe(
                    favoritesEntity
                )
            )
        }

    fun deleteFavoriteRecipe(vararg favoritesEntity: FavoritesEntityDomain) =
        viewModelScope.launch(Dispatchers.IO) {
            operationResult.postValue(
                removeFavoriteRecipeUseCase.removeFavoriteRecipe(
                    *favoritesEntity
                )
            )
        }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {
            operationResult.postValue(
                deleteAllFavoriteRecipeUseCase.deleteAll()
            )
        }
}