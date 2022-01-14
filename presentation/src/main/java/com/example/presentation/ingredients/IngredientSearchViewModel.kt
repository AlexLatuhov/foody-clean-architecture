package com.example.presentation.ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.ExtendedIngredientDomain
import com.example.domain.models.IngredientSearchDomain
import com.example.domain.usecase.interfaces.IngredientInfoUseCase
import com.example.domain.usecase.interfaces.IngredientSearchUseCase
import com.example.presentation.base.scopeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class IngredientSearchViewModel @Inject constructor(
    private val ingredientSearchUseCase: IngredientSearchUseCase,
    private val ingredientInfoUseCase: IngredientInfoUseCase//todo use it in separate ViewModel
) : ViewModel() {

    internal val messageState = MutableLiveData<IngredientSearchDomain>()
    internal val ingredientInfo = MutableLiveData<ExtendedIngredientDomain>()

    fun getIngredientsSearchResult(searchQuery: String) = scopeLaunch {
        messageState.postValue(
            ingredientSearchUseCase.getIngredientsSearchResult(searchQuery).first()
        )
    }

    fun getIngredientInfo(id: String) = scopeLaunch {
        ingredientInfo.postValue(
            ingredientInfoUseCase.getIngredientInfo(id).first()
        )
    }
}