package com.example.foody.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.domain.models.RecipeDomain
import com.example.foody.presentation.ui.recipes.RecipeViewHolder

abstract class BaseRecipesAdapter : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getResult(position))
    }

    abstract fun getResult(position: Int): RecipeDomain
}