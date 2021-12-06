package com.example.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.RecipeDomain
import com.example.presentation.Constants
import com.example.presentation.databinding.FragmentIngredientsBinding

class IngredientsFragment : Fragment() {

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val args = arguments
        val myBundle: RecipeDomain? = args?.getParcelable(Constants.RECIPE)
        setupRecyclerView(binding)
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }
        return binding.root
    }

    private fun setupRecyclerView(binding: FragmentIngredientsBinding) {
        binding.ingredientsRecyclerView.adapter = mAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}