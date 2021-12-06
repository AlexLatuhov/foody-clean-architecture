package com.example.presentation.joke

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.DataProviderRequestResult
import com.example.domain.models.FoodJokeDomain
import com.example.presentation.R
import com.example.presentation.databinding.FragmentFoodJokeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodJokeFragment : Fragment() {

    private val viewModel: FoodJokeViewModel by viewModels()
    private var _binding: FragmentFoodJokeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodJokeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.getFoodJoke()
        viewModel.foodJokeDataItemResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is DataProviderRequestResult.Success -> {
                    setJokeText(response)
                }
                is DataProviderRequestResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    setJokeText(response)
                }
                is DataProviderRequestResult.Loading -> {
                    Log.d("FOOD_JOKE", "Loading")
                }
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setJokeText(dataProviderRequestResult: DataProviderRequestResult<FoodJokeDomain>) {
        dataProviderRequestResult.data.let {
            binding.foodJokeTextView.text = dataProviderRequestResult.data?.text
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.food_joke_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_food_joke) {
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(EXTRA_TEXT, binding.foodJokeTextView.text)
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}