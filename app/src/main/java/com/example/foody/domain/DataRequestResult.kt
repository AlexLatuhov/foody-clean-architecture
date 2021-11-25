package com.example.foody.domain

sealed class DataRequestResult {
    object Success : DataRequestResult()
    object None : DataRequestResult()
    class Error(val message: String) : DataRequestResult()
}