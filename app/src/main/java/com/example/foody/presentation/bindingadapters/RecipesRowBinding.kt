package com.example.foody.presentation.bindingadapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foody.R
import com.example.foody.presentation.models.RecipeUi
import org.jsoup.Jsoup

class RecipesRowBinding {
    companion object {

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: RecipeUi) {
            Log.d("onRecipeClickListener", "called")
            recipeRowLayout.setOnClickListener {
                try {//todo complete
//                    val action =
//                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
//                    recipeRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                val colorToSet = ContextCompat.getColor(view.context, R.color.green)
                when (view) {
                    is TextView -> {
                        view.setTextColor(colorToSet)
                    }
                    is ImageView -> {
                        view.setColorFilter(colorToSet)
                    }
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load((imageUrl)) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        @BindingAdapter("parseHtml")
        @JvmStatic
        fun parseHtml(textView: TextView, description: String?) {
            if (description != null) {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }
        }
    }
}