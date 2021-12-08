package com.example.presentation.favorites

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.FavoritesEntityDomain

@BindingAdapter("viewVisibility", "setData", requireAll = false)
fun View.setDataAndViewVisibility(
    favoritesEntity: List<FavoritesEntityDomain>?,
    mAdapter: FavoriteRecipesAdapter?
) {
    val noData = favoritesEntity.isNullOrEmpty()
    when (this) {
        is RecyclerView -> {
            isInvisible = noData
            if (!noData) {
                favoritesEntity?.let { mAdapter?.setFavoritesData(favoritesEntity) }
            }
        }
        else -> {
            isVisible = noData
        }
    }
}