package com.example.presentation.favorites

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.request.OperationResult

import com.example.presentation.BaseRecipesAdapter
import com.example.presentation.R
import com.example.presentation.RecipesDiffUtil
import com.example.presentation.recipes.RecipeViewHolder
import com.google.android.material.snackbar.Snackbar

class FavoriteRecipesAdapter(
    private val requireActivity: FragmentActivity,
    private val favoritesViewModel: FavoritesViewModel
) : BaseRecipesAdapter(),
    ActionMode.Callback {
    init {
        favoritesViewModel.favOperationResult.observe(requireActivity, { result ->
            showSnackBar(requireActivity.getString(if (result is OperationResult.Success) R.string.recipes_removed else R.string.unknown_error))
            multiSelection = false
            selectedRecipes.clear()
            clearMode()
        })
    }

    private var multiSelection = false
    private var selectedRecipes = arrayListOf<FavoritesEntityDomain>()
    private var myViewHolder = arrayListOf<RecipeViewHolder>()
    private var favoritesEntity = emptyList<FavoritesEntityDomain>()
    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View

    override fun getResult(position: Int) = favoritesEntity[position].recipe

    override fun getItemCount() = favoritesEntity.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        rootView = holder.binding.root
        val item = favoritesEntity[position]
        holder.binding.recipesRawLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, item)
            } else {
                val action =
                    FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity(
                        getResult(position)
                    )
                holder.binding.root.findNavController().navigate(action)
            }
        }
        validateSelectionUi(holder, item)
        holder.binding.recipesRawLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
            }
            applySelection(holder, item)
            true
        }
        myViewHolder.add(holder)
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        mActionMode = mode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_favorite_recipe_menu) {
            favoritesViewModel.deleteFavoriteRecipe(*selectedRecipes.toTypedArray())
        }
        return true
    }

    private fun showSnackBar(string: String) {
        Snackbar.make(rootView, string, Snackbar.LENGTH_SHORT)
            .setAction(requireActivity.getString(android.R.string.ok)) {}
            .show()
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        multiSelection = false
        selectedRecipes.clear()
        myViewHolder.forEach {
            changeRecipeStyle(it, R.color.cardBackground, R.color.lightMediumGray)
        }
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyActionModeTitle() {
        when (selectedRecipes.size) {
            0 -> {
                mActionMode.finish()
                multiSelection = false
            }
            1 -> {
                mActionMode.title = requireActivity.getString(R.string.item_selected)
            }
            else -> {
                mActionMode.title =
                    requireActivity.getString(R.string.items_selected, selectedRecipes.size)
            }
        }
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

    private fun validateSelectionUi(
        holder: RecipeViewHolder,
        currentRecipe: FavoritesEntityDomain
    ) {
        if (!selectedRecipes.contains(currentRecipe)) {
            changeRecipeStyle(holder, R.color.cardBackground, R.color.lightMediumGray)
        } else {
            changeRecipeStyle(holder, R.color.cardBackgroundLight, R.color.colorPrimary)
        }
    }

    private fun applySelection(holder: RecipeViewHolder, currentRecipe: FavoritesEntityDomain) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            applyActionModeTitle()
        }
        validateSelectionUi(holder, currentRecipe)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor = ContextCompat.getColor(requireActivity, color)
    }

    fun clearMode() {
        if (this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }
}