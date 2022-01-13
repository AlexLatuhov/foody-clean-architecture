package com.example.presentation.ingredients

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityIngredientSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientSearchActivity : AppCompatActivity() {

    private val ingredientSearchViewModel: IngredientSearchViewModel by viewModels()

    private var _binding: ActivityIngredientSearchBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIngredientSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)//todo

        ingredientSearchViewModel.messageState.observe(this, { requestResult ->
            val testRes = requestResult.results[0]
            binding.testTextView.text = testRes.toString()
            ingredientSearchViewModel.getIngredientInfo(testRes.id.toString())
        })
        ingredientSearchViewModel.getIngredientsSearchResult("banana")

        ingredientSearchViewModel.ingredientInfo.observe(this, { result ->
            binding.testTextView.append("\nrequest specific info: $result")
        })

        binding.lifecycleOwner = this
        binding.viewModel = ingredientSearchViewModel
    }
}