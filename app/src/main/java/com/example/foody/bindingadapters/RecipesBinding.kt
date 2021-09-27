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

        private fun showError(
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) = apiResponse is NetworkResult.Error && database.isNullOrEmpty()

        @SuppressLint("SetTextI18n")
        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun errorViewVisibility(
            view: View,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            if (showError(apiResponse, database)) {
                view.visibility = View.VISIBLE
                if (view is TextView) {
                    view.text = apiResponse?.message
                }
            } else {
                view.visibility = View.INVISIBLE
            }
        }
    }
}