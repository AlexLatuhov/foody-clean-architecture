package com.example.foody.presentation

import com.example.foody.data.database.models.ExtendedIngredient
import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.data.database.models.Recipe
import com.example.foody.data.database.repositories.MealAndDietType
import com.example.foody.presentation.models.ExtendedIngredientUi
import com.example.foody.presentation.models.FavoritesEntityUi
import com.example.foody.presentation.models.MealAndDietTypeUi
import com.example.foody.presentation.models.RecipeUi
import javax.inject.Inject

class DomainToUiMapper @Inject constructor() {
    fun map(recipes: List<Recipe>) = recipes.map { it.convertToUiItem() }

    fun map(mealAndDietType: MealAndDietType) = mealAndDietType.convertToUiItem()

    fun map(favoritesEntity: FavoritesEntity) = favoritesEntity.convertToUiItem()

    private fun FavoritesEntity.convertToUiItem() = FavoritesEntityUi(id, recipe.convertToUiItem())

    private fun MealAndDietType.convertToUiItem() = MealAndDietTypeUi(
        selectedMealType,
        selectedMealTypeId,
        selectedDietType,
        selectedDietTypeId
    )

    private fun Recipe.convertToUiItem() = RecipeUi(
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