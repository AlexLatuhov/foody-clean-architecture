package com.example.foody.data.repositories

interface MealAndDietTypeSaver {

    fun saveMealAndDietTypeTemp(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    )

}