package com.example.domain.gateway

interface MealAndDietTypeSaver {

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )

}