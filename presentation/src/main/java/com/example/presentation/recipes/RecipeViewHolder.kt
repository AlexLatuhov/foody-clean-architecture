package com.example.presentation.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.domain.models.RecipeDomain
import com.example.presentation.databinding.RecipesRowLayoutPresentationBinding

class RecipeViewHolder(internal val binding: RecipesRowLayoutPresentationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: RecipeDomain) {
        binding.result = result
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): RecipeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = RecipesRowLayoutPresentationBinding.inflate(layoutInflater, parent, false)
            return RecipeViewHolder(binding)
        }
    }
}