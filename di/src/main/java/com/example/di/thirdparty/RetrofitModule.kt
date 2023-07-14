package com.example.di.thirdparty

import com.example.data.commons.Urls
import com.example.data.datasource.IRemoteRecipeDataSource
import com.example.data.datasource.remote.RemoteRecipeDataSource
import com.example.data.remote.service.RecipeService
import com.example.data.repository.RecipeRepository
import com.example.domain.repository.IRecipeRepository
import com.example.domain.usecase.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(retrofit: Retrofit): RecipeService {
        return retrofit.create(RecipeService::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteRecipeDataSource(
        recipeService: RecipeService
    ): IRemoteRecipeDataSource {
        return RemoteRecipeDataSource(recipeService)
    }
}

    /*@Singleton
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
    }*/
