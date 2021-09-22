package com.example.foody.bindingadapters

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.foody.data.database.entities.RecipesEntity
import com.example.foody.models.FoodRecipe
import com.example.foody.util.NetworkResult

class RecipesBinding {
    companion object {

        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibility(
            imageView: View,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (showError(apiResponse, database)) {
                imageView.visibility = View.VISIBLE
            } else {
                imageView.visibility = View.INVISIBLE
            }
        }

        private fun showError(
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) = apiResponse is NetworkResult.Error && database.isNullOrEmpty()

        @SuppressLint("SetTextI18n")
        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (showError(apiResponse, database)) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse?.message
            } else {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}