package com.example.foody.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.foody.R

class RecipesRowBinding {
    companion object {

        @BindingAdapter("setNumberToTextView")
        @JvmStatic
        fun setNumberToTextView(textView: TextView, number: Int) {
            textView.text = number.toString()
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
            }
        }
    }
}