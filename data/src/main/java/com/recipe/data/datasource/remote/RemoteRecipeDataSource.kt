package com.recipe.data.datasource.remote

import com.recipe.common.Either
import com.recipe.common.ErrorEntity
import com.recipe.data.mapper.toRecipe
import com.recipe.data.mapper.toRecipeDetail
import com.recipe.data.remote.service.RecipeService
import com.recipe.domain.model.Recipe
import com.recipe.domain.model.RecipeDetail

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