package com.example.foody.data

import android.content.Context
import com.example.foody.R
import com.example.foody.data.api.models.ExtendedIngredientDataItem
import com.example.foody.data.api.models.RecipeDataItem
import com.example.foody.data.api.models.ResultDataItem
import com.example.foody.data.database.models.ExtendedIngredient
import com.example.foody.data.database.models.FoodRecipe
import com.example.foody.data.database.models.Recipe
import com.example.foody.data.database.models.RecipesEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataToLocalDbMapper @Inject constructor(@ApplicationContext private val context: Context) {
    fun map(recipeDataItem: RecipeDataItem): RecipesEntity {
        val listOfResults = recipeDataItem.results.map { it.convertToLocalDbItem() }
        return RecipesEntity(FoodRecipe(listOfResults))
    }

    private fun ResultDataItem.convertToLocalDbItem(): Recipe {
        return Recipe(
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
            consistency ?: context.getString(R.string.no_value),
            image,
            name,
            original,
            unit
        )
    }
}