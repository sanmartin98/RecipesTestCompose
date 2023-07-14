package com.example.domain.repository

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail

interface IRecipeRepository {
    suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>>
    suspend fun getRecipeDetail(recipeId: String): Either<ErrorEntity, RecipeDetail>
}