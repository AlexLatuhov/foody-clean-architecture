package com.example.foody.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.domain.NetworkResult
import com.example.foody.domain.models.FoodJokeDomain
import com.example.foody.domain.usecase.GetFoodJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(
    private val getFoodJokeUseCase: GetFoodJokeUseCase,
    application: Application
) : AndroidViewModel(application) {
    var foodJokeDataItemResponse: MutableLiveData<NetworkResult<FoodJokeDomain>> = MutableLiveData()

    fun getFoodJoke() = viewModelScope.launch {
        foodJokeDataItemResponse.postValue(NetworkResult.Loading())
        getFoodJokeUseCase.getData()
            .collect { result -> foodJokeDataItemResponse.postValue(result) }
    }
}