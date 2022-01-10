package com.example.foody

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.presentation.recipes.RecipesFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class OverviewFragmentTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testOverviewFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<RecipesFragment>(Bundle(), R.style.AppTheme)
        {
            navController.setGraph(R.navigation.my_nav)
            navController.setCurrentDestination(R.id.recipesFragment)
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.shimmerFrameLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.title_textView)).check(matches(isDisplayed()))
        onView(ViewMatchers.withText("titleTest")).check(matches(isDisplayed()))
        onView(withId(R.id.recipesRawLayout)).perform(click())
//        onView(withId(R.id.detailsLayout)).check(matches(isDisplayed()))//todo?
    }
}