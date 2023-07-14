package com.example.data.repository

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.data.datasource.IRemoteRecipeDataSource
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail
import com.example.domain.repository.IRecipeRepository

/**
 * Test class [RecipeRepositoryTest]
 */
class RecipeRepository(
    private val iRemoteRecipeDataSource: IRemoteRecipeDataSource
): IRecipeRepository {
    override suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>> {
        return iRemoteRecipeDataSource.getRecipes()
    }

    override suspend fun getRecipeDetail(recipeId: String): Either<ErrorEntity, RecipeDetail> {
        return iRemoteRecipeDataSource.getRecipeDetail(recipeId)
    }
}