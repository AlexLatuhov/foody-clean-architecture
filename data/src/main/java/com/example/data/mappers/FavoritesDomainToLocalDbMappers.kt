package com.example.data.mappers

import com.example.data.database.models.ExtendedIngredient
import com.example.data.database.models.FavoritesEntity
import com.example.data.database.models.RecipeItemEntity
import com.example.domain.models.ExtendedIngredientDomain
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.RecipeDomain

fun FavoritesEntityDomain.convertToDataBaseItem() =
    FavoritesEntity(id, recipe.convertToDomainItem())

private fun RecipeDomain.convertToDomainItem() = RecipeItemEntity(
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

private fun ExtendedIngredientDomain.convertToDomainItem() =
    ExtendedIngredient(amount, consistency, image, name, original, unit)