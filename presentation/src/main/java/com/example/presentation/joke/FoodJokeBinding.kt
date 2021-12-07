package com.example.presentation.joke

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.domain.models.FoodJokeDomain
import com.example.domain.models.request.DataProviderRequestResult
import com.google.android.material.card.MaterialCardView

class FoodJokeBinding {
    companion object {

        @BindingAdapter("readApiResponseFoodJoke", requireAll = false)
        @JvmStatic
        fun setCardAndProgressVisibility(
            view: View,
            apiResponse: DataProviderRequestResult<FoodJokeDomain>?
        ) {
            when (apiResponse) {
                is DataProviderRequestResult.Loading -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.VISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }
                is DataProviderRequestResult.Error -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility =
                                if (apiResponse.data == null) View.INVISIBLE else View.VISIBLE
                        }
                    }
                }
                is DataProviderRequestResult.Success -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        @BindingAdapter("readApiResponseErrorView", requireAll = true)
        @JvmStatic
        fun setErrorViewsVisibility(
            view: View,
            apiResponse: DataProviderRequestResult<FoodJokeDomain>?
        ) {
            if (apiResponse != null && apiResponse.data == null) {
                view.visibility = View.VISIBLE
                if (view is TextView) {
                    view.text = apiResponse.message.toString()
                }
            }
            if (apiResponse is DataProviderRequestResult.Success || apiResponse is DataProviderRequestResult.Loading) {
                view.visibility = View.INVISIBLE
            }
        }
    }
}