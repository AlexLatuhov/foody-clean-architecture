package com.example.data.mappers

import com.example.data.database.models.ExtendedIngredient
import com.example.data.database.models.FavoritesEntity
import com.example.data.database.models.FoodJokeEntity
import com.example.data.database.models.RecipeItemEntity
import com.example.data.database.repositories.MealAndDietType
import com.example.domain.models.*

fun FoodJokeEntity.convertToDomain() = FoodJokeDomain(foodJokeDataItem.text)

fun List<RecipeItemEntity>.convertToDomainItem() = map { it.convertToDomainItem() }

fun FavoritesEntity.convertToDomainItem() =
    FavoritesEntityDomain(id, recipeItemEntity.convertToDomainItem())

fun MealAndDietType.convertToDomainItem() = MealAndDietTypeDomain(
    selectedMealType,
    selectedMealTypeId,
    selectedDietType,
    selectedDietTypeId
)

private fun RecipeItemEntity.convertToDomainItem() = RecipeDomain(
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

fun ExtendedIngredient.convertToDomainItem() =
    ExtendedIngredientDomain(amount, consistency, image, name, original, unit ?: "")