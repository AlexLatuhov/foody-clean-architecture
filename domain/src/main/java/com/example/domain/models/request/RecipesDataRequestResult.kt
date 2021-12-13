package com.example.domain.models.request

sealed class RecipesDataRequestResult {

    object Success : RecipesDataRequestResult()

    object None : RecipesDataRequestResult()

    sealed class Error : RecipesDataRequestResult()

    object NoConnectionError : Error()

    object NotFound : Error()

    object NoData : Error()

    object Timeout : Error()

    object ApiKetLimited : Error()

    object UnknownError : Error()

    class ErrorWithMessage(val message: String) : Error()
}