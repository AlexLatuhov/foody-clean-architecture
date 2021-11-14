package com.example.foody.util

class Constants {
    companion object {
        const val API_KEY = "ca3bc72fe3da446cb74b1752bb0dbe22"
        const val BASE_URL = "https://api.spoonacular.com"
        const val IMG_URL = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val TIMEOUT = 15L

        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_RECIPE_INFO = "addRecipeInformation"
        const val QUERY_INGREDIENTS = "fillIngredients"
        const val QUERY_DIET = "diet"
        const val QUERY_SEARCH = "query"

        const val DATABASE_NAME = "recipes_database_"
        const val RECIPES_TABLE = "recipes_table"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"
        const val FOOD_JOKE_TABLE = "food_joke"

        const val DEFAULT_ID = 0
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"
        const val DEFAULT_RECIPES_NUMBER = "50"
        const val PREF_MEAL_TYPE = "mealType"
        const val PREF_MEAL_TYPE_ID = "mealTypeId"
        const val PREF_DIET_TYPE = "dietType"
        const val PREF_DIET_TYPE_ID = "diedTypeId"
        const val BACK_ONLINE = "BACK_ONLINE"
        const val SEARCH_QUERY = "SEARCH_QUERY"
        const val PREF_NAME = "settings"
        const val RECIPE = "recipeBundle"
        const val TEST_TAG = "TEST_TAG"
        const val RECIPES = "RECIPES"
        const val ERROR_MESSAGE = "ERROR_MESSAGE"
        const val SELECTED_MEAL = "SELECTED_MEAL"
        const val SELECTED_DIET = "SELECTED_DIET"
    }
}