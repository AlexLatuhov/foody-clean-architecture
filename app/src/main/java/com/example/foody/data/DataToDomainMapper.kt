package com.example.foody.data

import com.example.foody.data.api.models.ExtendedIngredientDataItem
import com.example.foody.data.api.models.RecipeDataItem
import com.example.foody.data.api.models.ResultDataItem
import com.example.foody.data.database.models.ExtendedIngredient
import com.example.foody.data.database.models.FoodRecipe
import com.example.foody.data.database.models.RecipesEntity
import com.example.foody.data.database.models.Result
import javax.inject.Inject

class DataToDomainMapper @Inject constructor() {
    fun map(recipeDataItem: RecipeDataItem): RecipesEntity {
        val listOfResults = recipeDataItem.results.map { it.convertToDomainItem() }
        return RecipesEntity(FoodRecipe(listOfResults))
    }

    private fun ResultDataItem.convertToDomainItem(): Result {
        return Result(
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
    }

    private fun ExtendedIngredientDataItem.convertToDomainItem(): ExtendedIngredient {
        return ExtendedIngredient(amount, consistency ?: "no value", image, name, original, unit)
    }
}