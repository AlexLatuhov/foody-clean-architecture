package com.example.data.extentions

import retrofit2.Response

fun <T> Response<T>.wasKeyLimited() = code() == 402

