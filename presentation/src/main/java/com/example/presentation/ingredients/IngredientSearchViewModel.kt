package com.example.presentation.ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.gateway.IngredientInfoGateWay
import com.example.domain.gateway.IngredientSearchGateWay
import com.example.domain.models.ExtendedIngredientDomain
import com.example.domain.models.IngredientSearchDomain
import com.example.presentation.base.scopeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class IngredientSearchViewModel @Inject constructor(
    private val ingredientSearchGateWay: IngredientSearchGateWay,
    private val ingredientInfoGateWay: IngredientInfoGateWay//todo use it in separate ViewModel
) : ViewModel() {

    internal val messageState = MutableLiveData<IngredientSearchDomain>()
    internal val ingredientInfo = MutableLiveData<ExtendedIngredientDomain>()

    fun getIngredientsSearchResult(searchQuery: String) = scopeLaunch {
        messageState.postValue(
            ingredientSearchGateWay.getIngredientsSearchResult(searchQuery).first()
        )
    }

    fun getIngredientInfo(id: String) = scopeLaunch {
        ingredientInfo.postValue(
            ingredientInfoGateWay.getIngredientInfo(id).first()
        )
    }
}