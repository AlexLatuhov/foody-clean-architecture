package com.example.presentation.joke

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.DataProviderRequestResult
import com.example.domain.models.FoodJokeDomain
import com.example.domain.usecase.interfaces.GetFoodJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodJokeViewModel @Inject constructor(
    private val getFoodJokeUseCase: GetFoodJokeUseCase,
    application: Application
) : AndroidViewModel(application) {

    var foodJokeDataItemResponse: MutableLiveData<DataProviderRequestResult<FoodJokeDomain>> =
        MutableLiveData()

    fun getFoodJoke() = viewModelScope.launch {
        foodJokeDataItemResponse.postValue(DataProviderRequestResult.Loading())
        getFoodJokeUseCase.getData()
            .collect { result -> foodJokeDataItemResponse.postValue(result) }
    }
}