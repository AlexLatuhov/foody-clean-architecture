package com.example.presentation.favorites

import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.FavoritesEntityDomain
import com.example.presentation.R
import com.example.presentation.RecipesDiffUtil
import com.example.presentation.base.BaseRecipesAdapter
import com.example.presentation.recipes.RecipeViewHolder

class FavoriteRecipesAdapter(
    private val requireActivity: FragmentActivity,
    private val favoritesViewModel: SelectableFavoritesViewModel
) : BaseRecipesAdapter() {

    private var myViewHolder = arrayListOf<RecipeViewHolder>()

    private var favoritesEntity = emptyList<FavoritesEntityDomain>()

    override fun getResult(position: Int) = favoritesEntity[position].recipe

    override fun getItemCount() = favoritesEntity.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val pos = holder.bindingAdapterPosition
        val item = favoritesEntity[pos]
        holder.binding.recipeClickListener = object : RecipeClick {
            override fun onRecipeClick() {
                if (favoritesViewModel.multiSelection) {
                    applySelection(holder, item)
                } else {
                    val action =
                        FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity(
                            getResult(pos)
                        )
                    holder.binding.root.findNavController().navigate(action)
                }
            }
        }
        validateSelectionUi(holder, item)
        holder.binding.recipesRawLayout.setOnLongClickListener {
            favoritesViewModel.switchToMultiSelection(item)
            notifyItemChanged(pos)
            true
        }
        myViewHolder.add(holder)
    }

    private fun changeRecipeStyle(
        holder: RecipeViewHolder,
        backgroundColor: Int,
        strokeColor: Int
    ) {
        holder.binding.recipesRawLayout.setBackgroundColor(
            ContextCompat.getColor(
                requireActivity,
                backgroundColor
            )
        )
        holder.binding.rowCardView.strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    fun setFavoritesData(favoritesEntityValues: List<FavoritesEntityDomain>) {
        val recipesDiffUtil = RecipesDiffUtil(favoritesEntity, favoritesEntityValues)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        favoritesEntity = favoritesEntityValues
        diffUtilResult.dispatchUpdatesTo(this)
    }

    fun onActionEnded() {
        myViewHolder.forEach {
            changeRecipeStyle(it, R.color.cardBackground, R.color.lightMediumGray)
        }
    }

    private fun validateSelectionUi(
        holder: RecipeViewHolder,
        currentRecipe: FavoritesEntityDomain
    ) {
        if (!favoritesViewModel.isSelected(currentRecipe)) {
            changeRecipeStyle(holder, R.color.cardBackground, R.color.lightMediumGray)
        } else {
            changeRecipeStyle(holder, R.color.cardBackgroundLight, R.color.colorPrimary)
        }
    }

    private fun applySelection(holder: RecipeViewHolder, currentRecipe: FavoritesEntityDomain) {
        favoritesViewModel.applySelection(currentRecipe)
        validateSelectionUi(holder, currentRecipe)
    }
}