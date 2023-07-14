package com.example.di.provider

import android.content.Context
import com.example.data.datasource.local.ILocalRecipeDataSource
import com.example.data.datasource.remote.IRemoteRecipeDataSource
import com.example.data.network.INetworkManager
import com.example.data.network.NetworkManager
import com.example.data.repository.RecipeRepository
import com.example.domain.repository.IRecipeRepository
import com.example.domain.usecase.GetRecipeDetailUseCase
import com.example.domain.usecase.GetRecipesUseCase
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