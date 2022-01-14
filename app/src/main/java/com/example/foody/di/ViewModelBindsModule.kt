package com.example.foody.di

import com.example.data.database.repositories.LocalMealAndDietRepository
import com.example.data.gateways.*
import com.example.data.repositories.MealAndDietRepository
import com.example.domain.gateway.*
import com.example.domain.usecase.implementations.*
import com.example.domain.usecase.interfaces.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelBindsModule {

    @Binds
    abstract fun bindRequestRecipesUseCase(requestRecipesUseCaseImpl: RecipesDataInteractorImpl): RecipesDataInteractor

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
    abstract fun bindIngredientSearchUseCase(ingredientSearchUseCase: IngredientSearchUseCaseImpl): IngredientSearchUseCase

    @Binds
    abstract fun bindIngredientInfoUseCase(ingredientInfoUseCase: IngredientInfoUseCaseImpl): IngredientInfoUseCase

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
    abstract fun bindIngredientsSearchGateWay(ingredientSearchGateWayApi: IngredientSearchGateWayApi): IngredientSearchGateWay

    @Binds
    abstract fun bindIngredientInfoGateWay(ingredientInfoGateWayApi: IngredientInfoGateWayApi): IngredientInfoGateWay

    @Binds
    abstract fun bindDataStoreRepository(localDataStoreRepository: LocalMealAndDietRepository): MealAndDietRepository
}