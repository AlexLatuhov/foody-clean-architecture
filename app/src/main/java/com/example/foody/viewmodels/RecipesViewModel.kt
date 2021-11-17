package com.example.foody.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.R
import com.example.foody.data.DataStoreRepository
import com.example.foody.data.MealAndDietType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    var networkStatus = false
    var backOnline = false
    lateinit var mealAndDietType: MealAndDietType
    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        mealAndDietType = MealAndDietType(
            mealType, mealTypeId, dietType, dietTypeId
        )
    }

    fun hasTempValue(): Boolean {
        return this::mealAndDietType.isInitialized
    }

    fun saveMealAndDietType() {
        if (!hasTempValue()) return

        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealAndDietType)
        }
    }

    private fun saveBackOnline(backOnline: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveBackOnline(backOnline)
    }

    fun showNetworkStatus() {
        val app = getApplication<Application>()
        if (!networkStatus) {
            Toast.makeText(app, app.getString(R.string.no_internet_connection), Toast.LENGTH_SHORT)
                .show()
            saveBackOnline(true)
        } else if (networkStatus && backOnline) {
            Toast.makeText(app, app.getString(R.string.back_online), Toast.LENGTH_SHORT)
                .show()
            saveBackOnline(false)
        }
    }
}