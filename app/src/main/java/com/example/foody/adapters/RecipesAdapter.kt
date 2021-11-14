package com.example.foody.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.foody.models.Result
import com.example.foody.util.RecipesDiffUtil

class RecipesAdapter : BaseRecipesAdapter() {

    internal var recipes = emptyList<Result>()

    override fun getResult(position: Int): Result {
        return recipes[position]
    }

    fun setDataItems(items: List<Result>) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, items)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = items
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}