package com.example.foody.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.usecase.DeleteAllFavoriteRecipeUseCase
import com.example.foody.domain.usecase.InsertFavoriteRecipeUseCase
import com.example.foody.domain.usecase.ReadFavoriteRecipesUseCase
import com.example.foody.domain.usecase.RemoveFavoriteRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    application: Application,
    readFavoriteRecipesUseCase: ReadFavoriteRecipesUseCase,
    private val insertFavoriteRecipeUseCase: InsertFavoriteRecipeUseCase,
    private val removeFavoriteRecipeUseCase: RemoveFavoriteRecipeUseCase,
    private val deleteAllFavoriteRecipeUseCase: DeleteAllFavoriteRecipeUseCase
) : AndroidViewModel(application) {

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