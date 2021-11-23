package com.example.foody.presentation.recipes

import com.example.foody.domain.models.ExtendedIngredient
import com.example.foody.domain.models.Result
import javax.inject.Inject

class DomainToUiMapper @Inject constructor() {
    fun map(results: List<Result>) = results.map { it.convertToUiItem() }

    private fun Result.convertToUiItem(): RecipeUi {
        return RecipeUi(
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

    private fun ExtendedIngredient.convertToDomainItem(): ExtendedIngredientUi {
        return ExtendedIngredientUi(amount, consistency, image, name, original, unit)
    }
}