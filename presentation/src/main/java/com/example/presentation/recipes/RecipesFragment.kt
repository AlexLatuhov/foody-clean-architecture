package com.example.presentation.recipes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.request.RecipesDataRequestResult
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentRecipesBinding
import com.example.presentation.favorites.FavoritesViewModel
import com.example.presentation.favorites.RecipeClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : BaseFragment<FragmentRecipesBinding>(), SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private val recipesViewModel: RecipesViewModel by viewModels()

    private val mAdapter by lazy { RecipesAdapter() }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesBinding =
        FragmentRecipesBinding::inflate

    override fun setup() {
        binding.lifecycleOwner = this
        binding.mainViewModel = favoritesViewModel
        setHasOptionsMenu(true)
        setupRecyclerView()
        binding.recipeClickListener =
            object : RecipeClick {
                override fun onRecipeClick() {
                    findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showShimmerEffect()
        recipesViewModel.obtainRecipesData()
        recipesViewModel.recipesRequestResult.observe(viewLifecycleOwner, { requestResult ->
            onDataRequestResult(requestResult)
        })
        recipesViewModel.recipesData.observe(viewLifecycleOwner, { results ->
            validateErrorViewsVisibility(results.isNullOrEmpty())
            mAdapter.setDataItems(results ?: emptyList())
            hideShimmerEffect()
        })
        recipesViewModel.errorMessageState.observe(viewLifecycleOwner, { errorMessage ->
            setError(errorMessage)
        })
    }

    private fun onDataRequestResult(recipesDataRequestResult: RecipesDataRequestResult) {
        when (recipesDataRequestResult) {
            is RecipesDataRequestResult.Success -> {
                loadDataFromCache()
            }
            is RecipesDataRequestResult.Error -> {
                onError(recipesDataRequestResult)
            }
            else -> {
            }
        }
    }

    private fun onError(error: RecipesDataRequestResult.Error) {
        val errorMessage = when (error) {
            is RecipesDataRequestResult.NoConnectionError -> {
                getString(R.string.no_internet_connection)
            }
            is RecipesDataRequestResult.NotFound -> {
                getString(R.string.not_found)
            }
            is RecipesDataRequestResult.NoData -> {
                getString(R.string.no_data)
            }
            is RecipesDataRequestResult.ErrorWithMessage -> {
                error.message
            }
            else -> {
                getString(R.string.unknown_error)
            }
        }

        loadDataFromCache(errorMessage)
        Toast.makeText(
            requireContext(),
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun validateErrorViewsVisibility(show: Boolean) {
        val errorVisibility = if (show) View.VISIBLE else View.GONE
        binding.errorTextView.visibility = errorVisibility
        binding.errorImageView.visibility = errorVisibility
    }

    private fun loadDataFromCache(
        errorMessage: String = getString(R.string.no_data),
        searchQuery: String? = null
    ) {
        recipesViewModel.loadDataFromCache(searchQuery, errorMessage)
    }

    private fun setError(errorMessage: String) {
        binding.errorTextView.text = errorMessage
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.startShimmer()
        binding.recyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmer()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.recipes_menu, menu)
        val search = menu.findItem(R.id.search_menu)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        searchView?.setOnCloseListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            loadDataFromCache(getString(R.string.no_data), query)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }

    override fun onClose(): Boolean {
        loadDataFromCache()
        return false
    }
}