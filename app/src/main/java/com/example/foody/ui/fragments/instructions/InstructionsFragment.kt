package com.example.foody.ui.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.foody.R
import com.example.foody.models.Result
import com.example.foody.util.Constants

class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instructions, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE)
        val webView = view.findViewById<WebView>(R.id.instructions_webView)
        webView.webViewClient = object : WebViewClient() {}
        val websiteUrl: String = myBundle!!.sourceUrl
        webView.loadUrl(websiteUrl)
        return view
    }
}