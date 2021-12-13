package com.example.presentation.joke

import android.content.res.Resources
import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.example.presentation.R


fun DataProviderRequestResult<FoodJokeDomain>.getErrorString(resources: Resources) = when (this) {
    is DataProviderRequestResult.NotFoundError -> {
        resources.getString(R.string.not_found)
    }
    is DataProviderRequestResult.NoInternetError -> {
        resources.getString(R.string.no_internet_connection)
    }
    is DataProviderRequestResult.ErrorWithMessage -> {
        message
    }
    else -> {
        resources.getString(R.string.unknown_error)
    }
}