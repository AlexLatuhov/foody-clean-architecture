package com.example.foody.util

class Constants {
    companion object {
        const val API_KEY = "ca3bc72fe3da446cb74b1752bb0dbe22"
        const val BASE_URL = "https://api.spoonacular.com"
        const val TIMEOUT = 15L

        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_RECIPE_INFO = "addRecipeInformation"
        const val QUERY_INGREDIENTS = "fillIngredients"
        const val QUERY_DIET = "diet"

        const val DATABASE_NAME = "recipes_database_"
        const val RECIPES_TABLE = "recipes_table"
    }
}