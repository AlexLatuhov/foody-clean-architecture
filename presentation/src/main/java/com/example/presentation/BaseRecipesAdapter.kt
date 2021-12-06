package com.example.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.RecipeDomain
import com.example.presentation.recipes.RecipeViewHolder

abstract class BaseRecipesAdapter : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getResult(position))
    }

    abstract fun getResult(position: Int): RecipeDomain
}