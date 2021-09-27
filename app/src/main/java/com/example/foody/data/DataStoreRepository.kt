package com.example.foody.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.foody.util.Constants.Companion.BACK_ONLINE
import com.example.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foody.util.Constants.Companion.DEFAULT_ID
import com.example.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foody.util.Constants.Companion.PREF_DIET_TYPE
import com.example.foody.util.Constants.Companion.PREF_DIET_TYPE_ID
import com.example.foody.util.Constants.Companion.PREF_MEAL_TYPE
import com.example.foody.util.Constants.Companion.PREF_MEAL_TYPE_ID
import com.example.foody.util.Constants.Companion.PREF_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = PREF_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREF_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREF_MEAL_TYPE_ID)
        val selectedDiedType = stringPreferencesKey(PREF_DIET_TYPE)
        val selectedDiedTypeId = intPreferencesKey(PREF_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(BACK_ONLINE)
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    suspend fun saveMealAndDietType(
        mealAndDietType: MealAndDietType
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealAndDietType.selectedMealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealAndDietType.selectedMealTypeId
            preferences[PreferenceKeys.selectedDiedType] = mealAndDietType.selectedDietType
            preferences[PreferenceKeys.selectedDiedTypeId] = mealAndDietType.selectedDietTypeId
        }
    }

    val readBackOnline: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferenceKeys.backOnline] ?: false
        }

    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: DEFAULT_ID
            val selectedDietType = preferences[PreferenceKeys.selectedDiedType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[PreferenceKeys.selectedDiedTypeId] ?: DEFAULT_ID
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)