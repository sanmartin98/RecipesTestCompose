package com.recipe.data.datasource.remote

import com.recipe.common.Either
import com.recipe.common.ErrorEntity
import com.recipe.domain.model.Recipe
import com.recipe.domain.model.RecipeDetail

interface IRemoteRecipeDataSource {
    suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>>
    suspend fun getRecipeDetail(recipeId: String): Either<ErrorEntity, RecipeDetail>
}