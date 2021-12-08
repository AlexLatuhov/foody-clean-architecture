package com.example.presentation.favorites

import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.request.OperationResult
import com.example.presentation.BaseFragment
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFavoriteRecipesBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : BaseFragment<FragmentFavoriteRecipesBinding>() {

    private val favoritesViewModel: FavoritesViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavoriteRecipesBinding =
        FragmentFavoriteRecipesBinding::inflate
    private val mAdapter: FavoriteRecipesAdapter by lazy {
        FavoriteRecipesAdapter(requireActivity(), favoritesViewModel)
    }

    override fun setup() {
        binding.lifecycleOwner = this
        binding.mainViewModel = favoritesViewModel
        binding.mAdapter = mAdapter
        binding.favoriteRecipesRecyclerView.adapter = mAdapter
        binding.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        setHasOptionsMenu(true)
        favoritesViewModel.favOperationResult.observe(this, { result ->
            showSnackBar(getString(if (result is OperationResult.Success) R.string.deleted else R.string.unknown_error))
        })
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
        mAdapter.clearMode()
    }
}