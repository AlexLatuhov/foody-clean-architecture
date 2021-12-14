package com.example.presentation.recipe

import androidx.viewbinding.ViewBinding
import com.example.domain.models.RecipeDomain
import com.example.presentation.Constants
import com.example.presentation.base.BaseFragment

abstract class BaseRecipeInfoFragment<T : ViewBinding> : BaseFragment<T>() {

    override fun setup() {
        arguments?.getParcelable<RecipeDomain>(Constants.RECIPE)?.let {
            showRecipeItemInfo(it)
        }
    }

    abstract fun showRecipeItemInfo(recipeDomain: RecipeDomain)
}