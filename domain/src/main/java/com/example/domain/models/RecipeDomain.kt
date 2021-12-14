package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RecipeDomain(

    val aggregateLikes: Int,

    val cheap: Boolean,

    val dairyFree: Boolean,

    val extendedIngredients: @RawValue List<ExtendedIngredientDomain>,

    val glutenFree: Boolean,

    val id: Int,

    val image: String?,

    val readyInMinutes: Int,

    val sourceUrl: String,

    val summary: String,

    val title: String,

    val vegan: Boolean,

    val vegetarian: Boolean,

    val veryHealthy: Boolean,
) : Parcelable