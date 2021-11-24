package com.example.foody.di

import android.content.Context
import androidx.room.Room
import com.example.foody.domain.database.RecipesDao
import com.example.foody.domain.database.RecipesDataBase
import com.example.foody.domain.datamanage.LocalRecipesLoader
import com.example.foody.domain.datamanage.LocalRecipesSaver
import com.example.foody.domain.datamanage.RecipesLoader
import com.example.foody.domain.datamanage.RecipesSaver
import com.example.foody.presentation.util.Constants.Companion.DATABASE_NAME
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
    fun provideRecipesLoader(recipesDao: RecipesDao): RecipesLoader {
        return LocalRecipesLoader(recipesDao)
    }
}