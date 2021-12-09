package com.example.presentation.recipe

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.example.domain.models.RecipeDomain
import com.example.presentation.R
import com.example.presentation.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup

@BindingAdapter("onRecipeClickListener")
fun ConstraintLayout.onRecipeClickListener(result: RecipeDomain) {
    Log.d("onRecipeClickListener", "called")
    setOnClickListener {
        try {
            val action = RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
            findNavController().navigate(action)
        } catch (e: Exception) {
            Log.d("onRecipeClickListener", e.toString())
        }
    }
}

@BindingAdapter("applySelectedColor")
fun View.applySelectedColor(vegan: Boolean) {
    if (vegan) {
        val colorToSet = ContextCompat.getColor(context, R.color.green)
        when (this) {
            is TextView -> {
                setTextColor(colorToSet)
            }
            is ImageView -> {
                setColorFilter(colorToSet)
            }
        }
    }
}

@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(imageUrl: String) {
    load((imageUrl)) {
        crossfade(600)
        error(R.drawable.ic_error_placeholder)
    }
}

@BindingAdapter("parseHtml")
fun TextView.parseHtml(description: String?) {
    if (description != null) {
        text = Jsoup.parse(description).text()
    }
}