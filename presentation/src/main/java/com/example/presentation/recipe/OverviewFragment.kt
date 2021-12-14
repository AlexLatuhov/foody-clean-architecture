package com.example.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.models.RecipeDomain
import com.example.presentation.databinding.FragmentOverviewBinding

class OverviewFragment : BaseRecipeInfoFragment<FragmentOverviewBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOverviewBinding =
        FragmentOverviewBinding::inflate

    override fun showRecipeItemInfo(recipeDomain: RecipeDomain) {
        binding.result = recipeDomain
        binding.executePendingBindings()
    }
}