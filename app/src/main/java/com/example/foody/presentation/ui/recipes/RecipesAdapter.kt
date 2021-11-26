package com.example.foody.presentation.ui.recipes

import androidx.recyclerview.widget.DiffUtil
import com.example.foody.domain.models.RecipeDomain
import com.example.foody.presentation.adapters.BaseRecipesAdapter
import com.example.foody.presentation.util.RecipesDiffUtil

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