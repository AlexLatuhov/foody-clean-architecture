package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredientDomain(

    val amount: Double,

    val consistency: String?,

    val image: String?,

    val name: String,

    val original: String,

    val unit: String
) : Parcelable