package com.example.data.datasource

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail

interface IRemoteRecipeDataSource {
    suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>>
    suspend fun getRecipeDetail(recipeId: String): Either<ErrorEntity, RecipeDetail>
}