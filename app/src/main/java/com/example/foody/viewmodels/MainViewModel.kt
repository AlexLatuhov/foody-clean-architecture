package com.example.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.foody.database.entities.FavoritesEntity
import com.example.foody.database.entities.RecipesEntity
import com.example.foody.dataold.Repository
import com.example.foody.dataold.workers.RecipesWorker
import com.example.foody.util.Constants.Companion.RECIPES
import com.example.foody.util.Constants.Companion.SELECTED_DIET
import com.example.foody.util.Constants.Companion.SELECTED_MEAL
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

    fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) =
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


    fun getRecipes(
        selectedMealType: String,
        selectedDietType: String
    ): LiveData<WorkInfo> {
        val data = Data.Builder().putString(SELECTED_MEAL, selectedMealType)
            .putString(SELECTED_DIET, selectedDietType).build()
        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<RecipesWorker>().addTag(RECIPES).setInputData(data).build()
        val workManager = WorkManager.getInstance(getApplication())
        workManager.cancelUniqueWork(RECIPES)
        workManager.enqueue(uploadWorkRequest)
        return workManager.getWorkInfoByIdLiveData(uploadWorkRequest.id)
    }

}