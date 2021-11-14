package com.example.foody.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.foody.R
import com.example.foody.data.DataStoreRepository
import com.example.foody.data.MealAndDietType
import com.example.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
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
    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    private var mealAndDietType = readMealAndDietType.asLiveData().value
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

    fun saveMealAndDietType() =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealAndDietType)
        }

    private fun saveBackOnline(backOnline: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreRepository.saveBackOnline(backOnline)
    }

    fun getSelectedMealType(): String {
        return mealAndDietType?.selectedMealType ?: DEFAULT_MEAL_TYPE
    }

    fun getSelectedDietType(): String {
        return mealAndDietType?.selectedDietType ?: DEFAULT_DIET_TYPE
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