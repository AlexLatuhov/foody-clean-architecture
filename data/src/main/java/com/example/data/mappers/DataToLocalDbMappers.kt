package com.example.data.mappers

import com.example.data.api.models.ExtendedIngredientDataItem
import com.example.data.api.models.RecipeDataItem
import com.example.data.api.models.ResultDataItem
import com.example.data.database.models.ExtendedIngredient
import com.example.data.database.models.FoodRecipeEntity
import com.example.data.database.models.RecipeItemEntity
import com.example.data.database.models.RecipesEntity

fun RecipeDataItem.convertToLocalDbItem(): RecipesEntity {
    val listOfResults = results.map { it.convertToLocalDbItem() }
    return RecipesEntity(FoodRecipeEntity(listOfResults))
}

private fun ResultDataItem.convertToLocalDbItem(): RecipeItemEntity {
    return RecipeItemEntity(
        aggregateLikes,
        cheap,
        dairyFree,
        extendedIngredients.map { it.convertToLocalDbItem() },
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
}

private fun ExtendedIngredientDataItem.convertToLocalDbItem(): ExtendedIngredient {
    return ExtendedIngredient(
        amount,
        consistency,
        image,
        name,
        original,
        unit
    )
}