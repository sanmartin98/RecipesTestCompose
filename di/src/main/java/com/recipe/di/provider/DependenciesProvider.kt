package com.recipe.di.provider

import android.content.Context
import com.recipe.data.datasource.local.ILocalRecipeDataSource
import com.recipe.data.datasource.remote.IRemoteRecipeDataSource
import com.recipe.data.network.INetworkManager
import com.recipe.data.network.NetworkManager
import com.recipe.data.repository.RecipeRepository
import com.recipe.domain.repository.IRecipeRepository
import com.recipe.domain.usecase.GetRecipeDetailUseCase
import com.recipe.domain.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenciesProvider {
    @Singleton
    @Provides
    fun providesRecipeRepository(
        remoteRecipeDataSource: IRemoteRecipeDataSource,
        localRecipeDataSource: ILocalRecipeDataSource,
        networkManager: INetworkManager
    ): IRecipeRepository {
        return RecipeRepository(remoteRecipeDataSource, localRecipeDataSource, networkManager)
    }

    @Singleton
    @Provides
    fun providesGetRecipesUseCase(
        recipeRepository: IRecipeRepository
    ): GetRecipesUseCase {
        return GetRecipesUseCase(recipeRepository)
    }

    @Singleton
    @Provides
    fun providesGetRecipeDetailUseCase(
        recipeRepository: IRecipeRepository
    ): GetRecipeDetailUseCase {
        return GetRecipeDetailUseCase(recipeRepository)
    }

    @Singleton
    @Provides
    fun providesNetworkManager(
        @ApplicationContext context: Context
    ): INetworkManager {
        return NetworkManager(context)
    }
}