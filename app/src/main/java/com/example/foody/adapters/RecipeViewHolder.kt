package com.example.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.databinding.RecipesRowLayoutBinding
import com.example.foody.models.Result

class RecipeViewHolder(internal val binding: RecipesRowLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {
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