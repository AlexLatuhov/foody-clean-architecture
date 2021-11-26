package com.example.foody.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.usecase.ReadFavoriteRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    application: Application,
    readFavoriteRecipesUseCase: ReadFavoriteRecipesUseCase
) : AndroidViewModel(application) {

    val readFavoriteRecipes = readFavoriteRecipesUseCase.readFavoriteRecipes().asLiveData()

//    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) =//todo
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.insertFavoriteRecipes(favoritesEntity)
//        }

    fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntityDomain) =
        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.deleteFavoriteRecipe(favoritesEntity)
        }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.deleteAllFavoriteRecipes()
        }


}