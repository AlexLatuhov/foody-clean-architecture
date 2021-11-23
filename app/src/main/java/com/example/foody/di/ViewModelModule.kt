package com.example.foody.di

import android.content.Context
import com.example.foody.data.RemoteDataSource
import com.example.foody.data.RequestRecipesGatewayApi
import com.example.foody.domain.DataStoreRepository
import com.example.foody.domain.LocalDataStoreRepository
import com.example.foody.domain.recipes.RequestRecipesGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

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