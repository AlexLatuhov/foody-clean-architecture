package com.example.foody.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.RecipesDao
import com.example.data.database.RecipesDataBase
import com.example.data.database.repositories.LocalFavoriteRecipesEditor
import com.example.data.database.repositories.LocalFoodJokeStorage
import com.example.data.database.repositories.LocalRecipesLoader
import com.example.data.database.repositories.LocalRecipesSaver
import com.example.data.repositories.FavoriteRecipesEditor
import com.example.data.repositories.JokeStorage
import com.example.data.repositories.RecipesLoader
import com.example.data.repositories.RecipesSaver
import com.example.presentation.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, RecipesDataBase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDao(dataBase: RecipesDataBase) = dataBase.recipesDao()

    @Singleton
    @Provides
    fun provideRecipesSaver(recipesDao: RecipesDao): RecipesSaver {
        return LocalRecipesSaver(recipesDao)
    }

    @Singleton
    @Provides
    fun provideJokeSaver(recipesDao: RecipesDao): JokeStorage {
        return LocalFoodJokeStorage(recipesDao)
    }

    @Singleton
    @Provides
    fun provideRecipesLoader(recipesDao: RecipesDao): RecipesLoader {
        return LocalRecipesLoader(recipesDao)
    }

    @Singleton
    @Provides
    fun provideFavoriteRecipesEditor(recipesDao: RecipesDao): FavoriteRecipesEditor {
        return LocalFavoriteRecipesEditor(recipesDao)
    }
}