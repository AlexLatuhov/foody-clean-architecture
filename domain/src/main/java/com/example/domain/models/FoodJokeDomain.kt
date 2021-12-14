package com.example.domain.models


import com.google.gson.annotations.SerializedName

data class FoodJokeDomain(

    @SerializedName("text")
    val text: String
)