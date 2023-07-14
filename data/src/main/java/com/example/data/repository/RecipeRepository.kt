package com.example.data.repository

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.data.datasource.local.ILocalRecipeDataSource
import com.example.data.datasource.remote.IRemoteRecipeDataSource
import com.example.data.network.INetworkManager
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail
import com.example.domain.repository.IRecipeRepository

/**
 * Test class [RecipeRepositoryTest]
 */
class RecipeRepository(
    private val remoteRecipeDataSource: IRemoteRecipeDataSource,
    private val localRecipeDataSource: ILocalRecipeDataSource,
    private val networkManager: INetworkManager
): IRecipeRepository {
    override suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>> {
        return if (networkManager.isConnected()) {
            when (val response = remoteRecipeDataSource.getRecipes()) {
                is Either.Right -> {
                    localRecipeDataSource.insertAllRecipes(response.value)
                    Either.Right(response.value)
                } is Either.Left -> {
                Either.Left(response.error)
            }
            }
        } else {
            localRecipeDataSource.getRecipes()
        }

    }

    override suspend fun getRecipeDetail(recipeId: String): Either<ErrorEntity, RecipeDetail> {
        return remoteRecipeDataSource.getRecipeDetail(recipeId)
    }
}