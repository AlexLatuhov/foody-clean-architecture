package com.example.data.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredientDataItem(
    @SerializedName("amount")
    val amount: Double,

    @SerializedName("consistency")
    val consistency: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("original")
    val original: String,

    @SerializedName("unit")
    val unit: String
) : Parcelable