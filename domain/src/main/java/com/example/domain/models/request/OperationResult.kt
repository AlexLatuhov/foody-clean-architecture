package com.example.domain.models.request

sealed class OperationResult {
    open class Success : OperationResult()
    object Fail : OperationResult()
}