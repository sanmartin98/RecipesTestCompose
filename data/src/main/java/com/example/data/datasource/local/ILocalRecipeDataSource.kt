package com.example.data.datasource.local

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.domain.model.Recipe

interface ILocalRecipeDataSource {
    suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>>
    suspend fun insertAllRecipes(recipes: List<Recipe>)
}