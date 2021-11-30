package com.example.foody.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import retrofit2.Response

fun <T> Response<T>.getErrorMessage(): String? {
    return when {
        isSuccessful -> {
            null
        }
        message().toString().contains("timeout") -> {
            "Timeout"
        }
        code() == 402 -> {
            "API Key Limited"
        }
        else -> {
            message()
        }
    }
}

fun Context.hasInternetConnection(): Boolean {
    val connectivityManager =
        applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return capabilities.hasActiveConnections()
}

private fun NetworkCapabilities.hasActiveConnections(): Boolean {
    return when {
        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}