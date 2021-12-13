package com.example.presentation.favorites

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.request.OperationResult
import com.example.presentation.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFavoriteRecipesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment<FragmentFavoriteRecipesBinding>(),
    ActionMode.Callback {

    private val favoritesViewModel: SelectableFavoritesViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteRecipesBinding =
        FragmentFavoriteRecipesBinding::inflate

    private val mAdapter: FavoriteRecipesAdapter by lazy {
        FavoriteRecipesAdapter(requireActivity(), favoritesViewModel)
    }

    private var mActionMode: ActionMode? = null

    override fun setup() {
        binding.lifecycleOwner = this
        binding.mainViewModel = favoritesViewModel
        binding.mAdapter = mAdapter
        binding.favoriteRecipesRecyclerView.adapter = mAdapter
        binding.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        setHasOptionsMenu(true)

        favoritesViewModel.screenState.observe(this, { state ->
            when (state) {
                is SelectableFavoritesViewModel.ScreenState.StartAction -> {
                    requireActivity().startActionMode(this)
                    applyTitle()
                }
                is SelectableFavoritesViewModel.ScreenState.ClearMode -> {
                    clearMode()
                }
                is SelectableFavoritesViewModel.ScreenState.ApplyTitle -> {
                    applyTitle()
                }
                else -> {

                }
            }
        })

        favoritesViewModel.favOperationResult.observe(viewLifecycleOwner, { result ->
            showSnackBar(getString(if (result is OperationResult.Success) R.string.recipes_removed else R.string.unknown_error))
        })
    }

    private fun applyTitle() {
        val size = favoritesViewModel.selectedItemsSize()
        mActionMode?.title =
            resources.getQuantityString(R.plurals.items_selected_plurals, size, size)
    }

    private fun showSnackBar(string: String) {
        Snackbar.make(binding.root, string, Snackbar.LENGTH_SHORT)
            .setAction(this.getString(android.R.string.ok)) {}
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            favoritesViewModel.deleteAllFavoriteRecipes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearMode()
    }

    private fun clearMode() = mActionMode?.finish()

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorites_contextual_menu, menu)
        mActionMode = mode
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        if (item?.itemId == R.id.delete_favorite_recipe_menu) {
            favoritesViewModel.deleteSelected()
        }
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        favoritesViewModel.onActionEnded()
        mAdapter.onActionEnded()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireActivity(), color)
    }
}