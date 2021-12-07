package com.example.domain.gateway

interface MealAndDietTypeSaver {

    suspend fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )

}