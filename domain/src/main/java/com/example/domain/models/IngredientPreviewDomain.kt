package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientPreviewDomain(

    val id: Long,

    val image: String?,

    val name: String,
) : Parcelable