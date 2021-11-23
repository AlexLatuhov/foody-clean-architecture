package com.example.foody.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.presentation.recipes.RecipeUi

abstract class BaseRecipesAdapter : RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getResult(position))
    }

    abstract fun getResult(position: Int): RecipeUi
}