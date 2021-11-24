package com.example.foody.presentation.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.data.database.models.FoodJoke
import com.example.foody.presentation.util.NetworkResult
import com.google.android.material.card.MaterialCardView

class FoodJokeBinding {
    companion object {

        @BindingAdapter("readApiResponse3", "readDatabase3", requireAll = false)
        @JvmStatic
        fun setCardAndProgressVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            when (apiResponse) {
                is NetworkResult.Loading -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.VISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }
                is NetworkResult.Error -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility =
                                if (database != null && database.isEmpty()) View.INVISIBLE else View.VISIBLE
                        }
                    }
                }
                is NetworkResult.Success -> {
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

        @BindingAdapter("readApiResponse4", "readDatabase4", requireAll = true)
        @JvmStatic
        fun setErrorViewsVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            if (database?.isEmpty() != false) {
                view.visibility = View.VISIBLE
                if (view is TextView && apiResponse != null) {
                    view.text = apiResponse.message.toString()
                }
            }
            if (apiResponse is NetworkResult.Success || apiResponse is NetworkResult.Loading) {
                view.visibility = View.INVISIBLE
            }
        }
    }
}