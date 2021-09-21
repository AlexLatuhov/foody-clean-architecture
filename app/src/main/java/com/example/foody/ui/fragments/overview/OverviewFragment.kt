package com.example.foody.ui.fragments.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.example.foody.R
import com.example.foody.databinding.FragmentOverviewBinding
import com.example.foody.models.Result
import com.example.foody.util.Constants.Companion.RECIPE
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater, container, false)
        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE)
        showResult(myBundle, binding)
        return binding.root
    }

    private fun showResult(myBundle: Result?, binding: FragmentOverviewBinding) {
        if (myBundle == null) return

        binding.mainImageView.load(myBundle.image)
        binding.titleTextView.text = myBundle.title
        binding.likesTextView.text = myBundle.aggregateLikes.toString()
        binding.timeTextView.text = myBundle.readyInMinutes.toString()
        myBundle.summary.let {
            val summary = Jsoup.parse(it).text()
            binding.summary.text = summary
        }
        setColorByValue(
            myBundle.vegetarian,
            binding.vegetarianTextView,
            binding.vegetarianImageView
        )
        setColorByValue(myBundle.vegan, binding.veganTextView, binding.veganImageView)
        setColorByValue(
            myBundle.glutenFree,
            binding.glutenFreeTextView,
            binding.glutenFreeImageView
        )
        setColorByValue(myBundle.dairyFree, binding.dairyFreeTextView, binding.dairyFreeImageView)
        setColorByValue(myBundle.veryHealthy, binding.healthyTextView, binding.healthyImageView)
        setColorByValue(myBundle.cheap, binding.cheapTextView, binding.cheapImageView)
    }

    private fun setColorByValue(value: Boolean, text: TextView, image: ImageView) {
        if (value) {
            image.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            text.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
    }

}