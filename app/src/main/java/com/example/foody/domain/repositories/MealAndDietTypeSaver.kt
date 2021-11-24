package com.example.foody.domain.repositories

interface MealAndDietTypeSaver {
    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )
}