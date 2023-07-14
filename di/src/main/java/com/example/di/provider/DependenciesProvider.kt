package com.example.di.provider

import com.example.data.datasource.IRemoteRecipeDataSource
import com.example.data.repository.RecipeRepository
import com.example.domain.repository.IRecipeRepository
import com.example.domain.usecase.GetRecipeDetailUseCase
import com.example.domain.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependenciesProvider {
    @Singleton
    @Provides
    fun providesRecipeRepository(
        remoteRecipeDataSource: IRemoteRecipeDataSource
    ): IRecipeRepository {
        return RecipeRepository(remoteRecipeDataSource)
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
}