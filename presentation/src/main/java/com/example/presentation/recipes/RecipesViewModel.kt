package com.example.presentation.recipes

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.request.RecipesDataRequestResult
import com.example.domain.usecase.interfaces.LoadRecipesUseCase
import com.example.domain.usecase.interfaces.RecipesDataInteractor
import com.example.presentation.BaseViewModel
import com.example.presentation.Constants.Companion.CLEAN_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesDataInteractor: RecipesDataInteractor,
    private val loadRecipesUseCase: LoadRecipesUseCase
) : BaseViewModel() {

    val recipesRequestResultRecipes = MutableLiveData<RecipesDataRequestResult>()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = scopeLaunch {
        recipesDataInteractor.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun readMealAndDietType() = recipesDataInteractor.readMealAndDietType()

    fun loadDataFromCache(searchQuery: String?) = loadRecipesUseCase.loadDataFromCache(searchQuery)

    fun getData() {
        recipesRequestResultRecipes.value = RecipesDataRequestResult.None
        scopeLaunch {
            recipesDataInteractor.requestAndStoreRecipesData()
                .collect { dataRequestResult ->
                    Log.d(CLEAN_TAG, "onDataRequestResult in collect $dataRequestResult")
                    recipesRequestResultRecipes.postValue(dataRequestResult)
                }
        }
    }
}