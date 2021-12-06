package com.example.data.com.example.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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