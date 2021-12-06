package com.example.presentation.recipes

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.RecipeDomain
import com.example.presentation.BaseRecipesAdapter
import com.example.presentation.RecipesDiffUtil

class RecipesAdapter : BaseRecipesAdapter() {

    internal var recipes = emptyList<RecipeDomain>()

    override fun getResult(position: Int): RecipeDomain {
        return recipes[position]
    }

    fun setDataItems(items: List<RecipeDomain>) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, items)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = items
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}