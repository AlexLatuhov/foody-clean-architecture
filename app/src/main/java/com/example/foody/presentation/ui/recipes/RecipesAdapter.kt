package com.example.foody.presentation.ui.recipes

import androidx.recyclerview.widget.DiffUtil
import com.example.foody.presentation.adapters.BaseRecipesAdapter
import com.example.foody.presentation.models.RecipeUi
import com.example.foody.presentation.util.RecipesDiffUtil

class RecipesAdapter : BaseRecipesAdapter() {

    internal var recipes = emptyList<RecipeUi>()

    override fun getResult(position: Int): RecipeUi {
        return recipes[position]
    }

    fun setDataItems(items: List<RecipeUi>) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, items)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = items
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}