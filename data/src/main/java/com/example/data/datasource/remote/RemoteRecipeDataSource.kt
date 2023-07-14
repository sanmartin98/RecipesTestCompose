package com.example.data.datasource.remote

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.data.datasource.IRemoteRecipeDataSource
import com.example.data.remote.service.RecipeService
import com.example.data.mapper.toRecipe
import com.example.data.mapper.toRecipeDetail
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail

/**
 * Test class [RemoteRecipeDataSourceTest]
 */
class RemoteRecipeDataSource(
    private val service: RecipeService
): IRemoteRecipeDataSource {
    override suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>> = try {
        val response = service.getRecipes()
        if (response.isSuccessful) {
            response.body()?.let {
                Either.Right(it.map { dto -> dto.toRecipe() })
            } ?: Either.Left(ErrorEntity.EmptyResponseError)
        } else {
            Either.Left(ErrorEntity.NetworkError(response.code()))
        }
    } catch (e: Exception) {
        Either.Left(ErrorEntity.UnknownError(e))
    }

    override suspend fun getRecipeDetail(recipeId: String): Either<ErrorEntity, RecipeDetail> = try {
        val response = service.getRecipeDetail(recipeId)
        if (response.isSuccessful) {
            response.body()?.let {
                Either.Right(it.toRecipeDetail())
            } ?: Either.Left(ErrorEntity.EmptyResponseError)
        } else {
            Either.Left(ErrorEntity.NetworkError(response.code()))
        }
    } catch (e: Exception) {
        Either.Left(ErrorEntity.UnknownError(e))
    }
}