package com.example.foody.domain.recipes

interface MealAndDietTypeSaver {
    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )
}