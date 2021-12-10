package com.example.presentation.recipe

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.domain.models.FavoritesEntityDomain
import com.example.presentation.Constants.Companion.DEFAULT_ID
import com.example.presentation.Constants.Companion.RECIPE
import com.example.presentation.R
import com.example.presentation.SuccessAdd
import com.example.presentation.SuccessRemove
import com.example.presentation.databinding.ActivityDetailsBinding
import com.example.presentation.favorites.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private var _binding: ActivityDetailsBinding? = null

    private val binding get() = _binding!!

    private var savedRecipeId = DEFAULT_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailsBinding.inflate(layoutInflater)
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
        favoritesViewModel.favOperationResult.observe(this, { result ->
            val notifyStringId = when (result) {
                is SuccessAdd -> {
                    R.string.saved_to_favorites
                }
                is SuccessRemove -> {
                    R.string.removed_from_favorites
                }
                else -> {
                    R.string.unknown_error
                }
            }
            showSnackBar(getString(notifyStringId))
        })
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
                favoritesViewModel.insertFavoriteRecipe(FavoritesEntityDomain(recipe = args.result))
            } else {
                favoritesViewModel.deleteFavoriteRecipe(
                    FavoritesEntityDomain(
                        savedRecipeId,
                        args.result
                    )
                )
            }
        }
        return super.onOptionsItemSelected(item)
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