package com.example.foody.domain

import com.example.foody.data.database.models.ExtendedIngredient
import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.data.database.models.Recipe
import com.example.foody.domain.models.ExtendedIngredientDomain
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.domain.models.RecipeDomain
import javax.inject.Inject

class DomainToLocalDbMapper @Inject constructor() {
    fun map(vararg favoritesEntity: FavoritesEntityDomain) =
        favoritesEntity.map { it.convertToDataBaseItem() }

    private fun FavoritesEntityDomain.convertToDataBaseItem() =
        FavoritesEntity(id, recipe.convertToDomainItem())

    private fun RecipeDomain.convertToDomainItem() = Recipe(
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
}