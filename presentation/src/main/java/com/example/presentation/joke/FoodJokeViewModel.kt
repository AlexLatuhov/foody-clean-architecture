package com.example.presentation.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.example.domain.models.request.InfoTypeDomain
import com.example.domain.usecase.interfaces.GetFoodJokeUseCase
import com.example.presentation.base.scopeLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(
    private val getFoodJokeUseCase: GetFoodJokeUseCase
) : ViewModel() {

    var foodJokeDataItemResponse = MutableLiveData<DataProviderRequestResult<FoodJokeDomain>>()

    fun getFoodJoke(type: InfoTypeDomain) = scopeLaunch {
        foodJokeDataItemResponse.postValue(DataProviderRequestResult.Loading())
        foodJokeDataItemResponse.postValue(getFoodJokeUseCase.obtainFoodJokeData(type).first())
    }
}