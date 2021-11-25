package com.example.foody.data.database.models

sealed class DataRequestResult {
    //todo move to domain
    object Success : DataRequestResult()
    object None : DataRequestResult()
    class Error(val message: String) : DataRequestResult()
}