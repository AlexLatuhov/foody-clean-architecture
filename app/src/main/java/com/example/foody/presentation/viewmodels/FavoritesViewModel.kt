package com.example.foody.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.domain.usecase.ReadFavoriteRecipesUseCase
import com.example.foody.presentation.DomainToUiMapper
import com.example.foody.presentation.models.FavoritesEntityUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(//todo rename to favoritesViewModel
    application: Application,
    readFavoriteRecipesUseCase: ReadFavoriteRecipesUseCase,
    private val domainToUiMapper: DomainToUiMapper
) : AndroidViewModel(application) {

    val readFavoriteRecipes = readFavoriteRecipesUseCase.readFavoriteRecipes()
        .map { items -> items.map { domainToUiMapper.map(it) } }.asLiveData()

//    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) =//todo
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.insertFavoriteRecipes(favoritesEntity)
//        }

    fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntityUi) =
        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.deleteFavoriteRecipe(favoritesEntity)
        }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {
//            repository.local.deleteAllFavoriteRecipes()
        }


}