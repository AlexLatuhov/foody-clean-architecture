package com.example.foody.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foody.domain.database.entities.FavoritesEntity
import com.example.foody.domain.database.entities.FoodJokeEntity
import com.example.foody.domain.models.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class, FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDataBase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}