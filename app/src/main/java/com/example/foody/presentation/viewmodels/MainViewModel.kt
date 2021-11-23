package com.example.foody.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.domain.RecipesEntity
import com.example.foody.domain.database.entities.FavoritesEntity
import com.example.foody.presentation.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    val readFavoriteRecipes: LiveData<List<FavoritesEntity>> =
        repository.local.readFavoriteRecipes().asLiveData()
    val readRecipes: LiveData<List<RecipesEntity>> = repository.local.readRecipes().asLiveData()

    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) =//todo
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertFavoriteRecipes(favoritesEntity)
        }

    fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteFavoriteRecipe(favoritesEntity)
        }

    fun deleteAllFavoriteRecipes() =
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.deleteAllFavoriteRecipes()
        }



}