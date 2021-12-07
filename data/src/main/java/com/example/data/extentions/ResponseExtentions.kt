package com.example.data.extentions

import android.content.Context
import com.example.data.R
import retrofit2.Response


fun <T> Response<T>.getErrorMessage(context: Context): String? {
    return when {
        isSuccessful -> {
            null
        }
        message().toString().contains("timeout") -> {
            context.getString(R.string.timeout)
        }
        code() == 402 -> {
            context.getString(R.string.key_limited)
        }
        else -> {
            message()
        }
    }
}