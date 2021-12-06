package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.models.FavoritesEntity
import com.example.data.database.models.FoodJokeEntity
import com.example.data.database.models.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class, FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDataBase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}