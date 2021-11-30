package com.example.foody.data.api.models


import com.google.gson.annotations.SerializedName

data class FoodJokeDataItem(
    @SerializedName("text")
    val text: String
)