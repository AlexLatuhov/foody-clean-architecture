package com.example.foody.data.models

import com.google.gson.annotations.SerializedName

data class RecipeDataItem(
    @SerializedName("results")
    val results: List<ResultDataItem>
)