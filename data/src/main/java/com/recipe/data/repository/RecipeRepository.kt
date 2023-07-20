package com.recipe.data.repository

import com.recipe.common.Either
import com.recipe.common.ErrorEntity
import com.recipe.data.datasource.local.ILocalRecipeDataSource
import com.recipe.data.datasource.remote.IRemoteRecipeDataSource
import com.recipe.data.network.INetworkManager
import com.recipe.domain.model.Recipe
import com.recipe.domain.model.RecipeDetail
import com.recipe.domain.repository.IRecipeRepository

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