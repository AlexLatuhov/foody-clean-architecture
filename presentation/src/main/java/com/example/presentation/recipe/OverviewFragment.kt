package com.example.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.domain.models.RecipeDomain
import com.example.presentation.Constants.Companion.RECIPE
import com.example.presentation.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val args = arguments
        val myBundle: RecipeDomain? = args?.getParcelable(RECIPE)
        showResult(myBundle, binding)
        return binding.root
    }

    private fun showResult(myBundle: RecipeDomain?, binding: FragmentOverviewBinding) {
        if (myBundle == null) return

        binding.result = myBundle
        binding.executePendingBindings()
    }

}