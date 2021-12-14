package com.example.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.scopeLaunch(functionToLaunch: suspend () -> Unit) {
    viewModelScope.launch {
        functionToLaunch()
    }
}