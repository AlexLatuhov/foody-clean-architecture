package com.example.data.mappers

import com.example.data.api.models.ExtendedIngredientDataItem
import com.example.data.api.models.RecipeDataItem
import com.example.data.api.models.ResultDataItem
import com.example.data.database.models.ExtendedIngredient
import com.example.data.database.models.FoodRecipe
import com.example.data.database.models.Recipe
import com.example.data.database.models.RecipesEntity

fun RecipeDataItem.convertToLocalDbItem(defaultConsistency: String): RecipesEntity {
    val listOfResults = results.map { it.convertToLocalDbItem(defaultConsistency) }
    return RecipesEntity(FoodRecipe(listOfResults))
}

private fun ResultDataItem.convertToLocalDbItem(defaultConsistency: String): Recipe {
    return Recipe(
        aggregateLikes,
        cheap,
        dairyFree,
        extendedIngredients.map { it.convertToLocalDbItem(defaultConsistency) },
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

private fun ExtendedIngredientDataItem.convertToLocalDbItem(defaultConsistency: String): ExtendedIngredient {
    return ExtendedIngredient(
        amount,
        consistency ?: defaultConsistency,
        image,
        name,
        original,
        unit
    )
}