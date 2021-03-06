package com.example.data.database.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.Constants.DEFAULT_DIET_TYPE
import com.example.data.Constants.DEFAULT_ID
import com.example.data.Constants.DEFAULT_MEAL_TYPE
import com.example.data.Constants.PREF_DIET_TYPE
import com.example.data.Constants.PREF_DIET_TYPE_ID
import com.example.data.Constants.PREF_MEAL_TYPE
import com.example.data.Constants.PREF_MEAL_TYPE_ID
import com.example.data.Constants.PREF_NAME
import com.example.data.repositories.MealAndDietRepository
import com.example.domain.models.request.OperationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = PREF_NAME)

@Singleton
class LocalMealAndDietRepository @Inject constructor(@ApplicationContext private val context: Context) :
    MealAndDietRepository {

    private var mealAndDietType: MealAndDietType? = null

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREF_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREF_MEAL_TYPE_ID)
        val selectedDiedType = stringPreferencesKey(PREF_DIET_TYPE)
        val selectedDiedTypeId = intPreferencesKey(PREF_DIET_TYPE_ID)
    }

    private val readMealAndDietType = context.dataStore.data
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

    override suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        mealAndDietType = MealAndDietType(
            mealType, mealTypeId, dietType, dietTypeId
        )
    }

    private suspend fun getType() = mealAndDietType ?: readMealAndDietType().first()

    override suspend fun selectedDietType() = getType().selectedDietType

    override suspend fun selectedMealType() = getType().selectedMealType

    override suspend fun saveMealAndDietType(
    ) = try {
        if (mealAndDietType != null) {
            val mealAndDietType = getType()
            context.dataStore.edit { preferences ->
                preferences[PreferenceKeys.selectedMealType] = mealAndDietType.selectedMealType
                preferences[PreferenceKeys.selectedMealTypeId] = mealAndDietType.selectedMealTypeId
                preferences[PreferenceKeys.selectedDiedType] = mealAndDietType.selectedDietType
                preferences[PreferenceKeys.selectedDiedTypeId] = mealAndDietType.selectedDietTypeId
            }
        }
        OperationResult.Success()
    } catch (e: Exception) {
        OperationResult.Fail
    }

    override fun readMealAndDietType() = readMealAndDietType
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)