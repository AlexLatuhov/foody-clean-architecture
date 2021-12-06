package com.example.data.database

import androidx.room.TypeConverter
import com.example.data.database.models.FoodRecipeEntity
import com.example.data.database.models.RecipeItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipeEntity {
        val listType = object : TypeToken<FoodRecipeEntity>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun foodRecipeToString(foodRecipeEntity: FoodRecipeEntity): String {
        return gson.toJson(foodRecipeEntity)
    }

    @TypeConverter
    fun resultToString(recipeItemEntity: RecipeItemEntity): String {
        return gson.toJson(recipeItemEntity)
    }

    @TypeConverter
    fun stringToResult(data: String): RecipeItemEntity {
        val listType = object : TypeToken<RecipeItemEntity>() {}.type
        return gson.fromJson(data, listType)
    }
}