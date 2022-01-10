package com.example.foody

import com.example.domain.models.RecipeDomain
import com.example.domain.usecase.interfaces.LoadRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeLoadRecipesUseCase @Inject constructor() : LoadRecipesUseCase {
    private val fakeRecipeDomain = RecipeDomain(
        0,
        cheap = false,
        dairyFree = false,
        extendedIngredients = emptyList(),
        glutenFree = false,
        id = 1,
        image = "",
        readyInMinutes = 1,
        sourceUrl = "sourceUrlTest",
        summary = "",
        title = "titleTest",
        vegan = false,
        vegetarian = false,
        veryHealthy = false
    )

    override fun loadDataFromCache(searchQuery: String?): Flow<List<RecipeDomain>?> = flow {
        emit(listOf(fakeRecipeDomain))
    }
}