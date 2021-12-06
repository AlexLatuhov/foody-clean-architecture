package com.example.foody.di

import com.example.foody.data.database.repositories.LocalDataStoreRepository
import com.example.foody.data.gateways.DataFavoriteRecipesEditorGateway
import com.example.foody.data.gateways.DataLoadRecipeGateway
import com.example.foody.data.gateways.RequestFoodJokeGatewayApi
import com.example.foody.data.gateways.RequestRecipesGatewayApi
import com.example.foody.data.repositories.DataStoreRepository
import com.example.foody.domain.gateway.*
import com.example.foody.domain.usecase.implementations.*
import com.example.foody.domain.usecase.interfaces.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBindsModule {

    @Binds
    abstract fun bindRequestRecipesUseCase(requestRecipesUseCaseImpl: RequestRecipesUseCaseImpl): RequestRecipesUseCase

    @Binds
    abstract fun bindDeleteAllFavoriteRecipeUseCase(deleteAllFavoriteRecipeUseCaseImpl: DeleteAllFavoriteRecipeUseCaseImpl): DeleteAllFavoriteRecipeUseCase

    @Binds
    abstract fun bindGetFoodJokeUseCase(getFoodJokeUseCaseImpl: GetFoodJokeUseCaseImpl): GetFoodJokeUseCase

    @Binds
    abstract fun bindInsertFavoriteRecipeUseCase(insertFavoriteRecipeUseCaseImpl: InsertFavoriteRecipeUseCaseImpl): InsertFavoriteRecipeUseCase

    @Binds
    abstract fun bindLoadRecipesUseCase(loadRecipesUseCaseImpl: LoadRecipesUseCaseImpl): LoadRecipesUseCase

    @Binds
    abstract fun bindReadFavoriteRecipesUseCase(readFavoriteRecipesUseCaseImpl: ReadFavoriteRecipesUseCaseImpl): ReadFavoriteRecipesUseCase

    @Binds
    abstract fun bindRemoveFavoriteRecipeUseCase(removeFavoriteRecipeUseCaseImpl: RemoveFavoriteRecipeUseCaseImpl): RemoveFavoriteRecipeUseCase

    @Binds
    abstract fun bindLoadRecipesGateway(dataLoadRecipeGateway: DataLoadRecipeGateway): LoadRecipesGateway

    @Binds
    abstract fun bindInsertFavoriteRecipeGateWay(dataFavoriteRecipesEditorGateway: DataFavoriteRecipesEditorGateway): InsertFavoriteRecipeGateWay

    @Binds
    abstract fun bindRemoveFavoriteRecipeGateWay(dataFavoriteRecipesEditorGateway: DataFavoriteRecipesEditorGateway): RemoveFavoriteRecipeGateWay

    @Binds
    abstract fun bindDeleteAllFavoriteRecipeGateWay(dataFavoriteRecipesEditorGateway: DataFavoriteRecipesEditorGateway): DeleteAllFavoriteRecipeGateWay

    @Binds
    abstract fun bindReadFavoriteRecipesGateWay(dataLoadRecipeGateway: DataLoadRecipeGateway): ReadFavoriteRecipesGateWay

    @Binds
    abstract fun bindRequestRecipesGateway(requestRecipesGatewayApi: RequestRecipesGatewayApi): RequestRecipesGateway

    @Binds
    abstract fun bindGetFoodJokeGateway(requestFoodJokeGatewayApi: RequestFoodJokeGatewayApi): GetFoodJokeGateway

    @Binds
    abstract fun bindDataStoreRepository(localDataStoreRepository: LocalDataStoreRepository): DataStoreRepository
}