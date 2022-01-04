package com.example.presentation.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.models.FavoritesEntityDomain
import com.example.domain.models.RecipeDomain
import com.example.domain.models.request.OperationResult
import com.example.presentation.MainCoroutineRule
import com.example.presentation.SuccessAdd
import com.example.presentation.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoritesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var fakeInsertFavoriteRecipeUseCase: FakeInsertFavoriteRecipeUseCase
    private val fakeRecipeDomain = RecipeDomain(
        0,
        false,
        false,
        emptyList(),
        false,
        1,
        "",
        1,
        "",
        "",
        "",
        false,
        false,
        false
    )

    @Before
    fun setup() {
        fakeInsertFavoriteRecipeUseCase = FakeInsertFavoriteRecipeUseCase()
        favoritesViewModel = FavoritesViewModel(
            FakeReadFavoriteRecipesUseCase(),
            fakeInsertFavoriteRecipeUseCase,
            FakeRemoveFavoriteRecipeUseCaseImpl(),
            FakeDeleteAllFavoriteRecipeUseCase()
        )
    }

    @Test
    fun testInsert() {
        favoritesViewModel.insertFavoriteRecipe(FavoritesEntityDomain(recipe = fakeRecipeDomain))
        MatcherAssert.assertThat(
            favoritesViewModel.favOperationResult.getOrAwaitValue(),
            CoreMatchers.`is`(SuccessAdd)
        )

        fakeInsertFavoriteRecipeUseCase.operationResult = OperationResult.Fail
        favoritesViewModel.insertFavoriteRecipe(FavoritesEntityDomain(recipe = fakeRecipeDomain))
        MatcherAssert.assertThat(
            favoritesViewModel.favOperationResult.getOrAwaitValue(),
            CoreMatchers.`is`(OperationResult.Fail)
        )
    }
}