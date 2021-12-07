package com.example.presentation.recipes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.DataRequestResult
import com.example.domain.usecase.interfaces.LoadRecipesUseCase
import com.example.domain.usecase.interfaces.RequestRecipesUseCase
import com.example.presentation.Constants.Companion.CLEAN_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val requestRecipesUseCase: RequestRecipesUseCase,
    private val loadRecipesUseCase: LoadRecipesUseCase
) : AndroidViewModel(application) {

    val recipesRequestResult: MutableLiveData<DataRequestResult> = MutableLiveData()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = viewModelScope.launch(Dispatchers.IO)
    {
        requestRecipesUseCase.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun readMealAndDietType() = requestRecipesUseCase.readMealAndDietType()

    fun loadDataFromCache(searchQuery: String?) = loadRecipesUseCase.loadDataFromCache(searchQuery)


    fun getData() {
        recipesRequestResult.value = DataRequestResult.None
        viewModelScope.launch(Dispatchers.IO) {
            requestRecipesUseCase.getData()
                .collect { dataRequestResult ->
                    Log.d(CLEAN_TAG, "onDataRequestResult in collect $dataRequestResult")
                    recipesRequestResult.postValue(dataRequestResult)
                }
        }
    }
}