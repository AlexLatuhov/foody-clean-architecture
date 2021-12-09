package com.example.presentation.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.example.domain.models.RecipeDomain
import com.example.presentation.databinding.RecipesRowLayoutPresentationBinding
import com.example.presentation.favorites.RecipeClick

class RecipeViewHolder(internal val binding: RecipesRowLayoutPresentationBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: RecipeDomain) {
        binding.result = result
        binding.recipeClickListener = object : RecipeClick {
            override fun onRecipeClick() {
                binding.root.findNavController().navigate(
                    RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(
                        result
                    )
                )
            }
        }
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