package com.example.foody.presentation.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.domain.DataRequestResult
import com.example.foody.domain.MealAndDietType
import com.example.foody.domain.recipes.RequestRecipesUseCase
import com.example.foody.presentation.recipes.DomainToUiMapper
import com.example.foody.presentation.util.Constants.Companion.CLEAN_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    val domainToUiMapper: DomainToUiMapper,
    private val requestRecipesUseCase: RequestRecipesUseCase
) : AndroidViewModel(application) {

    val recipesRequestResult: MutableLiveData<DataRequestResult> = MutableLiveData()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        requestRecipesUseCase.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun readMealAndDietType(): Flow<MealAndDietType> {
        return requestRecipesUseCase.readMealAndDietType()
    }

    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            requestRecipesUseCase.getData().collect { dataRequestResult ->
                Log.d(CLEAN_TAG, "onDataRequestResult in collect $dataRequestResult")
                recipesRequestResult.postValue(dataRequestResult)
            }
        }
    }
}