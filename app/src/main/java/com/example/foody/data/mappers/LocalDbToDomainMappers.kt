package com.example.foody.data.mappers

import com.example.foody.data.database.models.ExtendedIngredient
import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.data.database.models.FoodJokeEntity
import com.example.foody.data.database.models.Recipe
import com.example.foody.data.database.repositories.MealAndDietType
import com.example.foody.domain.models.*

fun FoodJokeEntity.convertToDomain() = FoodJokeDomain(foodJokeDataItem.text)

fun List<Recipe>.convertToDomainItem() = map { it.convertToDomainItem() }

fun FavoritesEntity.convertToDomainItem() =
    FavoritesEntityDomain(id, recipe.convertToDomainItem())

fun MealAndDietType.convertToDomainItem() = MealAndDietTypeDomain(
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