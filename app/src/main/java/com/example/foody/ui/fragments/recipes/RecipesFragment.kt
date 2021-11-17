package com.example.foody.ui.fragments.recipes

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkInfo
import com.example.foody.R
import com.example.foody.adapters.RecipesAdapter
import com.example.foody.data.MealAndDietType
import com.example.foody.databinding.FragmentRecipesBinding
import com.example.foody.util.Constants.Companion.ERROR_MESSAGE
import com.example.foody.util.NetworkListener
import com.example.foody.viewmodels.MainViewModel
import com.example.foody.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var networkListener: NetworkListener
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        setHasOptionsMenu(true)
        setupRecyclerView()
        recipesViewModel.readBackOnline.observe(viewLifecycleOwner, {
            recipesViewModel.backOnline = it
        })

        binding.recipesFab.setOnClickListener {
            if (recipesViewModel.networkStatus) {
                findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
            } else {
                recipesViewModel.showNetworkStatus()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkListener = NetworkListener()
        lifecycleScope.launchWhenStarted {
            networkListener.checkNetwork(requireContext()).collect { status ->
                recipesViewModel.networkStatus = status
                recipesViewModel.showNetworkStatus()
                getFilterData()
            }
        }
    }

    private fun getFilterData() {
        showShimmerEffect()
        if (recipesViewModel.hasTempValue()) {
            requestApiData(recipesViewModel.mealAndDietType)
            return
        }
        recipesViewModel.readMealAndDietType.asLiveData().observe(viewLifecycleOwner, { value ->
            requestApiData(value)
        })
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
        showShimmerEffect()
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, { database ->
                hideShimmerEffect()
                val resultsTemp = database.getOrNull(0)?.foodRecipe?.results
                val results =
                    if (searchQuery != null)
                        resultsTemp?.filter { result ->
                            result.title.contains(
                                searchQuery,
                                true
                            )
                        }
                    else resultsTemp
                if (results.isNullOrEmpty()) {
                    setError(errorMessage)
                }
                validateErrorViewsVisibility(results.isNullOrEmpty())
                mAdapter.setDataItems(results ?: emptyList())
            })
        }
    }

    private fun setError(errorMessage: String) {
        binding.errorTextView.text = errorMessage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
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

    private fun requestApiData(value: MealAndDietType) {
        mainViewModel.getRecipes(
            value.selectedMealType,
            value.selectedDietType
        ).observe(viewLifecycleOwner, { workInfo ->
            val state = workInfo?.state
            if (state == WorkInfo.State.SUCCEEDED) {
                recipesViewModel.saveMealAndDietType()
                loadDataFromCache()
            } else if (state == WorkInfo.State.FAILED) {
                val errorMessage = workInfo.outputData.getString(ERROR_MESSAGE)
                loadDataFromCache(
                    errorMessage
                        ?: getString(R.string.unknown_error)
                )
                Toast.makeText(
                    requireContext(),
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (state?.isFinished == true) {
                hideShimmerEffect()
            }
        })
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