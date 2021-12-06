package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.database.models.FoodRecipe
import com.example.data.database.models.Recipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun resultToString(recipe: Recipe): String {
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun stringToResult(data: String): Recipe {
        val listType = object : TypeToken<Recipe>() {}.type
        return gson.fromJson(data, listType)
    }
}