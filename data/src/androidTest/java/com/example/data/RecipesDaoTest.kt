package com.example.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.data.database.RecipesDataBase
import com.example.data.database.models.ExtendedIngredient
import com.example.data.database.models.FoodRecipeEntity
import com.example.data.database.models.RecipeItemEntity
import com.example.data.database.models.RecipesEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RecipesDaoTest {

    private lateinit var database: RecipesDataBase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RecipesDataBase::class.java
        ).build()
    }

    @Test
    fun insertRecipeTest() = runBlockingTest {
        val ingredient = ExtendedIngredient(0.0, "", "", "", "", "")
        val entity = RecipeItemEntity(
            1,
            cheap = true,
            dairyFree = true,
            extendedIngredients = listOf(ingredient),
            glutenFree = true,
            id = 3,
            image = "",
            readyInMinutes = 5,
            sourceUrl = "sourceUrl",
            summary = "",
            title = "title",
            vegan = true,
            vegetarian = true,
            veryHealthy = true
        )
        val recipeEntity = RecipesEntity(FoodRecipeEntity(listOf(entity)))

        val result = database.recipesDao().insertRecipes(recipeEntity)
        val loaded = database.recipesDao().readRecipes().first()
        assertThat(result, `is`(0))
        assert(loaded[0].foodRecipeEntity.recipeItemEntities.isNotEmpty())
        assertThat(loaded[0].foodRecipeEntity.recipeItemEntities[0].sourceUrl, `is`("sourceUrl"))
    }

    @After
    fun closeDb() = database.close()
}