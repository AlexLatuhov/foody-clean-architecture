package com.example.data.api.models

import com.google.gson.annotations.SerializedName

data class RecipeDataItem(
    @SerializedName("results")
    val results: List<ResultDataItem>
)