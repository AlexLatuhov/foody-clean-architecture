package com.example.foody.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.data.Constants.Companion.DEFAULT_ID
import com.example.foody.data.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: FoodRecipe) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = DEFAULT_ID
}