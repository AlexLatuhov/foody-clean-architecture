package com.example.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.models.RecipeDomain
import com.example.presentation.databinding.FragmentIngredientsBinding

class IngredientsFragment : BaseRecipeInfoFragment<FragmentIngredientsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientsBinding =
        FragmentIngredientsBinding::inflate

    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun showRecipeItemInfo(recipeDomain: RecipeDomain) {
        binding.ingredientsRecyclerView.adapter = mAdapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeDomain.extendedIngredients.let { mAdapter.setData(it) }
    }
}