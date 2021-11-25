package com.example.foody.presentation.bindingadapters

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foody.presentation.adapters.FavoriteRecipesAdapter
import com.example.foody.presentation.models.FavoritesEntityUi

class FavoriteRecipesBinding {
    companion object {

        @BindingAdapter("viewVisibility", "setData", requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            favoritesEntity: List<FavoritesEntityUi>?,
            mAdapter: FavoriteRecipesAdapter?
        ) {
            val noData = favoritesEntity.isNullOrEmpty()
            when (view) {
                is RecyclerView -> {
                    view.isInvisible = noData
                    if (!noData) {
                        favoritesEntity?.let { mAdapter?.setFavoritesData(favoritesEntity) }
                    }
                }
                else -> {
                    view.isVisible = noData
                }
            }
        }
    }
}