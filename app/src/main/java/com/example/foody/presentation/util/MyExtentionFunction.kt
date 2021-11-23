package com.example.foody.presentation.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

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