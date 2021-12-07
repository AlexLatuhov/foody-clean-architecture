package com.example.presentation.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.request.RecipesDataRequestResult
import com.example.domain.usecase.interfaces.LoadRecipesUseCase
import com.example.domain.usecase.interfaces.RecipesDataInteractor
import com.example.presentation.Constants.Companion.CLEAN_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesDataInteractor: RecipesDataInteractor,
    private val loadRecipesUseCase: LoadRecipesUseCase
) : ViewModel() {

    val recipesRequestResultRecipes: MutableLiveData<RecipesDataRequestResult> = MutableLiveData()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = viewModelScope.launch(Dispatchers.IO)
    {
        recipesDataInteractor.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun readMealAndDietType() = recipesDataInteractor.readMealAndDietType()

    fun loadDataFromCache(searchQuery: String?) = loadRecipesUseCase.loadDataFromCache(searchQuery)

    fun getData() {
        recipesRequestResultRecipes.value = RecipesDataRequestResult.None
        viewModelScope.launch(Dispatchers.IO) {
            recipesDataInteractor.requestAndStoreRecipesData()
                .collect { dataRequestResult ->
                    Log.d(CLEAN_TAG, "onDataRequestResult in collect $dataRequestResult")
                    recipesRequestResultRecipes.postValue(dataRequestResult)
                }
        }
    }
}