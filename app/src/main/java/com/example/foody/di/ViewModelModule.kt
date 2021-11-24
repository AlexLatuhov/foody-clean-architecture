package com.example.foody.di

import android.content.Context
import com.example.foody.data.DataLoadRecipesGateway
import com.example.foody.data.RemoteDataSource
import com.example.foody.data.RequestRecipesGatewayApi
import com.example.foody.domain.datamanage.DataStoreRepository
import com.example.foody.domain.datamanage.LocalDataStoreRepository
import com.example.foody.domain.datamanage.RecipesLoader
import com.example.foody.domain.usecase.LoadRecipesGateway
import com.example.foody.domain.usecase.RequestRecipesGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideLoadRecipesGateway(recipesLoader: RecipesLoader): LoadRecipesGateway {
        return DataLoadRecipesGateway(recipesLoader)
    }

    @Provides
    fun provideRequestRecipesGateway(
        @ApplicationContext context: Context,
        localDataStoreRepository: LocalDataStoreRepository,
        remoteDataSource: RemoteDataSource
    ): RequestRecipesGateway {
        return RequestRecipesGatewayApi(context, localDataStoreRepository, remoteDataSource)
    }

    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context): DataStoreRepository {
        return LocalDataStoreRepository(context)
    }
}