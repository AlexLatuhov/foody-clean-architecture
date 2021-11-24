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
import com.example.foody.presentation.adapters.PagerAdapter
import com.example.foody.presentation.ui.fragments.ingredients.IngredientsFragment
import com.example.foody.presentation.ui.fragments.instructions.InstructionsFragment
import com.example.foody.presentation.ui.fragments.overview.OverviewFragment
import com.example.foody.presentation.util.Constants.Companion.DEFAULT_ID
import com.example.foody.presentation.util.Constants.Companion.RECIPE
import com.example.foody.presentation.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {//todo complete favorites flow

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()
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
        mainViewModel.readFavoriteRecipes.observe(this, { favoritesEntity ->
            try {
                changeMenuItemColor(menuItem, R.color.white)
                savedRecipeId = DEFAULT_ID
                for (savedRecipe in favoritesEntity) {
                    if (savedRecipe.result.id == args.result.id) {
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
                saveToFavorites(item)
            } else {
                removeFromFavorites(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(item: MenuItem) {
//        val favoritesEntity = FavoritesEntity(0, args.result)
//        mainViewModel.insertFavoriteRecipe(favoritesEntity)
//        changeMenuItemColor(item, R.color.yellow)
//        showSnackBar(getString(R.string.saved_to_favorites))
    }

    private fun removeFromFavorites(item: MenuItem) {
//        val favoritesEntity = FavoritesEntity(savedRecipeId, args.result)
//        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
//        changeMenuItemColor(item, R.color.white)
//        showSnackBar(getString(R.string.removed_from_favorites))
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