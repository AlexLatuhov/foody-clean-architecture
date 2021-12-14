package com.example.presentation.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MealAndDietTypeDomain
import com.example.domain.models.RecipeDomain
import com.example.domain.models.request.RecipesDataRequestResult
import com.example.domain.usecase.interfaces.LoadRecipesUseCase
import com.example.domain.usecase.interfaces.RecipesDataInteractor
import com.example.presentation.base.scopeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesDataInteractor: RecipesDataInteractor,
    private val loadRecipesUseCase: LoadRecipesUseCase
) : ViewModel() {

    val recipesRequestResult = MutableLiveData<RecipesDataRequestResult>()

    val recipesData = MutableLiveData<List<RecipeDomain>>()

    val errorMessageState = MutableLiveData<String>()

    val mealAndDietType = MutableLiveData<MealAndDietTypeDomain>()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) = scopeLaunch {
        recipesDataInteractor.saveMealAndDietTypeTemp(mealType, mealTypeId, dietType, dietTypeId)
    }

    fun readMealAndDietType() = scopeLaunch {
        mealAndDietType.postValue(recipesDataInteractor.readMealAndDietType().first())
    }

    fun loadDataFromCache(searchQuery: String?, errorMessage: String) = scopeLaunch {
        val queryResult = loadRecipesUseCase.loadDataFromCache(searchQuery).first() ?: emptyList()
        recipesData.postValue(queryResult)
        errorMessageState.postValue(if (queryResult.isEmpty()) errorMessage else null)
    }

    fun obtainRecipesData() {
        recipesRequestResult.value = RecipesDataRequestResult.None
        scopeLaunch {
            recipesRequestResult.postValue(
                recipesDataInteractor.obtainRecipesData().first()
            )
        }
    }
}