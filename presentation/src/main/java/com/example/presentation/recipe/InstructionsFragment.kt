package com.example.presentation.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.domain.models.RecipeDomain
import com.example.presentation.databinding.FragmentInstructionsBinding

class InstructionsFragment : BaseRecipeInfoFragment<FragmentInstructionsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentInstructionsBinding =
        FragmentInstructionsBinding::inflate

    override fun showRecipeItemInfo(recipeDomain: RecipeDomain) {
        val webView = binding.instructionsWebView
        webView.webViewClient = object : WebViewClient() {}
        webView.loadUrl(recipeDomain.sourceUrl)
    }
}