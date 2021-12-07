package com.example.domain.models.request

sealed class RecipesDataRequestResult {
    object Success : RecipesDataRequestResult()
    object None : RecipesDataRequestResult()
    class Error(val message: String) : RecipesDataRequestResult()
}