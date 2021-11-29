package com.example.foody.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.foody.R
import com.example.foody.databinding.ActivityDetailsBinding
import com.example.foody.domain.models.FavoritesEntityDomain
import com.example.foody.presentation.adapters.PagerAdapter
import com.example.foody.presentation.ui.fragments.ingredients.IngredientsFragment
import com.example.foody.presentation.ui.fragments.instructions.InstructionsFragment
import com.example.foody.presentation.ui.fragments.overview.OverviewFragment
import com.example.foody.presentation.util.Constants.Companion.DEFAULT_ID
import com.example.foody.presentation.util.Constants.Companion.RECIPE
import com.example.foody.presentation.viewmodels.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val favoritesViewModel: FavoritesViewModel by viewModels()
    private lateinit var binding: ActivityDetailsBinding
    private var savedRecipeId = DEFAULT_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())
        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE, args.result)

        val pagerAdapter = PagerAdapter(resultBundle, fragments, this)
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        val titles = ArrayList<Int>()
        titles.add(R.drawable.ic_overview)
        titles.add(R.drawable.ic_ingredients)
        titles.add(R.drawable.ic_instructions)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            tab.icon = ContextCompat.getDrawable(this, titles[pos])
        }.attach()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.save_to_favorites_menu)
        checkSavedRecipes(menuItem!!)
        return true
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        favoritesViewModel.readFavoriteRecipes.observe(this, { favoritesEntity ->
            try {
                changeMenuItemColor(menuItem, R.color.white)
                savedRecipeId = DEFAULT_ID
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.recipe.id == args.result.id) {
                        changeMenuItemColor(menuItem, R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        break
                    }
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu) {
            if (savedRecipeId == DEFAULT_ID) {
                saveToFavorites()
            } else {
                removeFromFavorites()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites() {
        favoritesViewModel.operationResult.observe(this, { result ->
            showSnackBar(getString(if (result) R.string.saved_to_favorites else R.string.unknown_error))
        })
        favoritesViewModel.insertFavoriteRecipe(FavoritesEntityDomain(recipe = args.result))
    }

    private fun removeFromFavorites() {
        favoritesViewModel.operationResult.observe(this, { result ->
            showSnackBar(getString(if (result) R.string.removed_from_favorites else R.string.unknown_error))
        })
        val favoritesEntity = FavoritesEntityDomain(savedRecipeId, args.result)
        favoritesViewModel.deleteFavoriteRecipe(favoritesEntity)
    }

    private fun showSnackBar(string: String) {
        Snackbar.make(binding.detailsLayout, string, Snackbar.LENGTH_SHORT)
            .setAction(getString(android.R.string.ok)) {}
            .show()

    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }
}