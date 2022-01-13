package com.example.data.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientPreviewDataItem(
    @SerializedName("id")
    val id: Long,

    @SerializedName("image")
    val image: String?,

    @SerializedName("name")
    val name: String,
) : Parcelable