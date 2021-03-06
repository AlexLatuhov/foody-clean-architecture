package com.example.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.Constants.DEFAULT_ID
import com.example.data.Constants.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipeEntity: FoodRecipeEntity) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = DEFAULT_ID
}