package com.example.presentation.joke

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.google.android.material.card.MaterialCardView

@BindingAdapter("readApiResponseFoodJoke", requireAll = false)
fun View.setCardAndProgressVisibility(
    apiResponse: DataProviderRequestResult<FoodJokeDomain>?
) {
    when (apiResponse) {
        is DataProviderRequestResult.Loading -> {
            when (this) {
                is ProgressBar -> {
                    visibility = View.VISIBLE
                }
                is MaterialCardView -> {
                    visibility = View.INVISIBLE
                }
            }
        }
        is DataProviderRequestResult.Error -> {
            when (this) {
                is ProgressBar -> {
                    visibility = View.INVISIBLE
                }
                is MaterialCardView -> {
                    visibility =
                        if (apiResponse.data == null) View.INVISIBLE else View.VISIBLE
                }
            }
        }
        is DataProviderRequestResult.Success -> {
            when (this) {
                is ProgressBar -> {
                    visibility = View.INVISIBLE
                }
                is MaterialCardView -> {
                    visibility = View.VISIBLE
                }
            }
        }
    }
}

@BindingAdapter("readApiResponseErrorView", requireAll = true)
fun View.setErrorViewsVisibility(
    apiResponse: DataProviderRequestResult<FoodJokeDomain>?
) {
    if (apiResponse != null && apiResponse.data == null) {
        visibility = View.VISIBLE
        if (this is TextView) {
            text = apiResponse.message.toString()
        }
    }
    if (apiResponse is DataProviderRequestResult.Success || apiResponse is DataProviderRequestResult.Loading) {
        visibility = View.INVISIBLE
    }
}