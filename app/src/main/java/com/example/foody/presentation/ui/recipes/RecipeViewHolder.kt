package com.example.foody.presentation.ui.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.databinding.RecipesRowLayoutBinding
import com.example.foody.presentation.recipes.RecipeUi

class RecipeViewHolder(internal val binding: RecipesRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: RecipeUi) {
        binding.result = result
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): RecipeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
            return RecipeViewHolder(binding)
        }
    }
}