package com.example.foody

import com.example.data.database.repositories.LocalMealAndDietRepository
import com.example.data.gateways.DataFavoriteRecipesEditorGateway
import com.example.data.gateways.DataLoadRecipeGateway
import com.example.data.gateways.RequestFoodJokeGatewayApi
import com.example.data.gateways.RequestRecipesGatewayApi
import com.example.data.repositories.MealAndDietRepository
import com.example.domain.gateway.*
import com.example.domain.usecase.implementations.*
import com.example.domain.usecase.interfaces.*
import com.example.foody.di.ViewModelBindsModule
import dagger.Binds
import dagger.Module
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn


@Module
@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [ViewModelBindsModule::class]
)
abstract class FakeViewModelComponent {
    @Binds
    abstract fun bindRequestRecipesUseCase(fakeRecipesDataInteractor: FakeRecipesDataInteractor): RecipesDataInteractor

    @Binds
    abstract fun bindDeleteAllFavoriteRecipeUseCase(deleteAllFavoriteRecipeUseCaseImpl: DeleteAllFavoriteRecipeUseCaseImpl): DeleteAllFavoriteRecipeUseCase

    @Binds
    abstract fun bindGetFoodJokeUseCase(getFoodJokeUseCaseImpl: GetFoodJokeUseCaseImpl): GetFoodJokeUseCase

    @Binds
    abstract fun bindInsertFavoriteRecipeUseCase(insertFavoriteRecipeUseCaseImpl: InsertFavoriteRecipeUseCaseImpl): InsertFavoriteRecipeUseCase

    @Binds
    abstract fun bindLoadRecipesUseCase(fakeLoadRecipesUseCase: FakeLoadRecipesUseCase): LoadRecipesUseCase

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
    abstract fun bindDataStoreRepository(localDataStoreRepository: LocalMealAndDietRepository): MealAndDietRepository

}