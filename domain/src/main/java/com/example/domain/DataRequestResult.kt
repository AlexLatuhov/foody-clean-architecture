package com.example.domain

sealed class DataRequestResult {
    object Success : DataRequestResult()
    object None : DataRequestResult()
    class Error(val message: String) : DataRequestResult()
}