package com.example.foody.presentation

import com.example.foody.domain.MealAndDietType
import com.example.foody.domain.models.ExtendedIngredient
import com.example.foody.domain.models.Result
import com.example.foody.presentation.recipes.ExtendedIngredientUi
import com.example.foody.presentation.recipes.MealAndDietTypeUi
import com.example.foody.presentation.recipes.RecipeUi
import javax.inject.Inject

class DomainToUiMapper @Inject constructor() {
    fun map(results: List<Result>) = results.map { it.convertToUiItem() }

    fun map(mealAndDietType: MealAndDietType) = mealAndDietType.convertToUiItem()

    private fun MealAndDietType.convertToUiItem() = MealAndDietTypeUi(
        selectedMealType,
        selectedMealTypeId,
        selectedDietType,
        selectedDietTypeId
    )

    private fun Result.convertToUiItem() = RecipeUi(
        aggregateLikes,
        cheap,
        dairyFree,
        extendedIngredients.map { it.convertToDomainItem() },
        glutenFree,
        id,
        image,
        readyInMinutes,
        sourceUrl,
        summary,
        title,
        vegan,
        vegetarian,
        veryHealthy
    )

    private fun ExtendedIngredient.convertToDomainItem() =
        ExtendedIngredientUi(amount, consistency, image, name, original, unit)
}