package com.example.domain.models.request

sealed class DataProviderRequestResult<T>(
    val data: T? = null
) {
    class Success<T>(data: T) : DataProviderRequestResult<T>(data)

    sealed class Error<T>(data: T? = null) :
        DataProviderRequestResult<T>(data)

    class UnknownError<T>(data: T?) : Error<T>(data)

    class NotFoundError<T>(data: T?) : Error<T>(data)

    class NoInternetError<T>(data: T?) : Error<T>(data)

    class Loading<T> : DataProviderRequestResult<T>()

    class ErrorWithMessage<T>(val message: String, data: T? = null) : Error<T>(data)

    class Timeout<T>(data: T?) : Error<T>(data)

    class ApiKetLimited<T>(data: T?) : Error<T>(data)
}