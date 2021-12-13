package com.example.data.extentions

import retrofit2.Response


fun <T> Response<T>.wasTimeout() = !isSuccessful && message().toString().contains("timeout")

fun <T> Response<T>.wasKeyLimited() = code() == 402

