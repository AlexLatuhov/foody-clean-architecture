package com.example.foody.domain

import com.example.foody.data.database.models.ExtendedIngredient
import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.data.database.models.Recipe
import com.example.foody.data.database.repositories.MealAndDietType
import com.example.foody.domain.models.ExtendedIngredientDomain
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.models.MealAndDietTypeDomain
import com.example.foody.domain.models.RecipeDomain
import javax.inject.Inject

class LocalDbToDomainMapper @Inject constructor() {
    fun map(recipes: List<Recipe>) = recipes.map { it.convertToDomainItem() }

    fun map(mealAndDietType: MealAndDietType) = mealAndDietType.convertToDomainItem()

    fun map(favoritesEntity: FavoritesEntity) = favoritesEntity.convertToDomainItem()

    private fun FavoritesEntity.convertToDomainItem() =
        FavoritesEntityDomain(id, recipe.convertToDomainItem())

    private fun MealAndDietType.convertToDomainItem() = MealAndDietTypeDomain(
        selectedMealType,
        selectedMealTypeId,
        selectedDietType,
        selectedDietTypeId
    )

    private fun Recipe.convertToDomainItem() = RecipeDomain(
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
        ExtendedIngredientDomain(amount, consistency, image, name, original, unit)
}