package com.example.domain.models.request

sealed class FavOperationResult {
    open class Success : FavOperationResult()
    object Error : FavOperationResult()
}