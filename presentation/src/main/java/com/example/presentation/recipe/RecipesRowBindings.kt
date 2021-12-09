package com.example.presentation.recipe

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.presentation.R
import org.jsoup.Jsoup

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