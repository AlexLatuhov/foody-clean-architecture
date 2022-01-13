package com.example.data.api.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class IngredientSearchResponse(
    @SerializedName("results")
    val results: List<IngredientPreviewDataItem>
) : Parcelable