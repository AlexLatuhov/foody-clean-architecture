package com.example.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.domain.models.ExtendedIngredientDomain
import com.example.presentation.Constants.Companion.IMG_URL
import com.example.presentation.R
import com.example.presentation.RecipesDiffUtil
import com.example.presentation.databinding.IngredientsRowLayoutBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<ExtendedIngredientDomain>()

    class MyViewHolder(private val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExtendedIngredientDomain) {
            binding.bindItem(item)
        }

        private fun IngredientsRowLayoutBinding.bindItem(item: ExtendedIngredientDomain) {
            ingredientImageView.load(IMG_URL + item.image) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
            ingredientName.text = item.getPrintName()
            ingredientAmount.text = item.amount.toString()
            ingredientUnit.text = item.unit
            ingredientConsistency.text = item.consistency
            ingredientOriginal.text = item.original
        }

        private fun ExtendedIngredientDomain.getPrintName() =
            if (name.isNotEmpty()) name.replaceFirstChar { it.titlecase() } else ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = IngredientsRowLayoutBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(ingredientsList[position])
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    fun setData(ingredients: List<ExtendedIngredientDomain>) {
        val recipesDiffUtil = RecipesDiffUtil(ingredientsList, ingredientsList)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        ingredientsList = ingredients
        diffUtilResult.dispatchUpdatesTo(this)
    }
}