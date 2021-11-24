package com.example.foody.domain.datamanage

interface MealAndDietTypeSaver {
    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )
}