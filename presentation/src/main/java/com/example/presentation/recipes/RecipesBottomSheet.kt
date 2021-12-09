package com.example.presentation.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.presentation.Constants
import com.example.presentation.databinding.RecipesBottomSheetBinding
import com.example.presentation.favorites.RecipeClick
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

class RecipesBottomSheet : BottomSheetDialogFragment() {

    private val recipesViewModel: RecipesViewModel by lazy { ViewModelProvider(requireActivity())[RecipesViewModel::class.java] }

    private var selectedMealType = Constants.DEFAULT_MEAL_TYPE
    private var selectedMealTypeId = Constants.DEFAULT_ID
    private var selectedDietType = Constants.DEFAULT_DIET_TYPE
    private var selectedDietTypeId = Constants.DEFAULT_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = RecipesBottomSheetBinding.inflate(inflater, container, false)

        recipesViewModel.readMealAndDietType().asLiveData().observe(viewLifecycleOwner, { value ->
            selectedMealType = value.selectedMealType
            selectedDietType = value.selectedDietType
            updateChip(value.selectedMealTypeId, binding.mealTypeChipGroup)
            updateChip(value.selectedDietTypeId, binding.dietTypeChipGroup)
        })

        binding.mealTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val mealType = chip.text.toString().lowercase(Locale.ROOT)
            selectedMealType = mealType
            selectedMealTypeId = selectedChipId
        }

        binding.dietTypeChipGroup.setOnCheckedChangeListener { group, selectedChipId ->
            val chip = group.findViewById<Chip>(selectedChipId)
            val type = chip.text.toString().lowercase(Locale.ROOT)
            selectedDietType = type
            selectedDietTypeId = selectedChipId
        }

        binding.recipeClickListener =
            object : RecipeClick {
                override fun onRecipeClick() {
                    recipesViewModel.saveMealAndDietTypeTemp(
                        selectedMealType,
                        selectedMealTypeId,
                        selectedDietType,
                        selectedDietTypeId
                    )
                    findNavController().navigate(RecipesBottomSheetDirections.actionRecipesBottomSheetToRecipesFragment())
                }
            }
        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                val targetView = chipGroup.findViewById<Chip>(chipId)
                targetView.isChecked = true
                chipGroup.requestChildFocus(targetView, targetView)
            } catch (e: Exception) {
                Log.d("INFO", e.message.toString())
            }
        }
    }

}