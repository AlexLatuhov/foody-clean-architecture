package com.example.domain

sealed class DataProviderRequestResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : DataProviderRequestResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : DataProviderRequestResult<T>(data, message)
    class Loading<T> : DataProviderRequestResult<T>()
}