package com.example.presentation.recipes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.DataRequestResult
import com.example.presentation.R
import com.example.presentation.databinding.FragmentRecipesBinding
import com.example.presentation.favorites.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = favoritesViewModel
        setHasOptionsMenu(true)
        setupRecyclerView()

        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showShimmerEffect()
        recipesViewModel.getData()
        recipesViewModel.recipesRequestResult.observe(viewLifecycleOwner, { requestResult ->
            onDataRequestResult(requestResult)
        })
    }

    private fun onDataRequestResult(dataRequestResult: DataRequestResult) {
        when (dataRequestResult) {
            is DataRequestResult.Success -> {
                loadDataFromCache()
            }
            is DataRequestResult.Error -> {
                val errorMessage = dataRequestResult.message
                loadDataFromCache(
                    errorMessage
                )
                Toast.makeText(
                    requireContext(),
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
            }
        }
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
        recipesViewModel.loadDataFromCache(searchQuery)
            .asLiveData().observe(viewLifecycleOwner, { results ->
                if (results.isNullOrEmpty()) {
                    setError(errorMessage)
                }
                validateErrorViewsVisibility(results.isNullOrEmpty())
                mAdapter.setDataItems(results ?: emptyList())
                hideShimmerEffect()
            })
    }

    private fun setError(errorMessage: String) {
        binding.errorTextView.text = errorMessage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesViewModel =
            ViewModelProvider(requireActivity()).get(FavoritesViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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