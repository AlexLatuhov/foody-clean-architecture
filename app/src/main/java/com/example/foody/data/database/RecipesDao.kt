package com.example.foody.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.foody.data.database.entities.FoodJokeEntity
import com.example.foody.data.database.models.FavoritesEntity
import com.example.foody.data.database.models.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert(onConflict = REPLACE)
    fun insertRecipes(recipesEntity: RecipesEntity): Long

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity): Long

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity): Int

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAll(): Int

    @Insert(onConflict = REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("SELECT * FROM food_joke ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>
}