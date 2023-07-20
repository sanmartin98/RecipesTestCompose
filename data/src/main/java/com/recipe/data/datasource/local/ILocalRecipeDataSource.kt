package com.recipe.data.datasource.local

import com.recipe.common.Either
import com.recipe.common.ErrorEntity
import com.recipe.domain.model.Recipe

interface ILocalRecipeDataSource {
    suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>>
    suspend fun insertAllRecipes(recipes: List<Recipe>)
}