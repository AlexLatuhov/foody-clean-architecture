package com.example.foody.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.databinding.FragmentFavoriteRecipesBinding
import com.example.foody.presentation.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    //    private val mAdapter: FavoriteRecipesAdapter by lazy {
//        FavoriteRecipesAdapter(
//            requireActivity(),
//            mainViewModel
//        )
//    }
    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
//        binding.mAdapter = mAdapter
//        binding.favoriteRecipesRecyclerView.adapter = mAdapter
        binding.favoriteRecipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun showSnackBar(string: String) {
        Snackbar.make(binding.root, string, Snackbar.LENGTH_SHORT)
            .setAction(this.getString(android.R.string.ok)) {}
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            mainViewModel.deleteAllFavoriteRecipes()
            showSnackBar(getString(R.string.deleted))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
//        mAdapter.clearMode()
    }
}