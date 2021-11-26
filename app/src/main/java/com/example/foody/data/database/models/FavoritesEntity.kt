package com.example.foody.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.data.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var recipe: Recipe
)