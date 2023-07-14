package com.example.data.datasource.local

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.data.local.dao.RecipeDao
import com.example.data.mapper.toRecipe
import com.example.data.mapper.toRecipeEntity
import com.example.domain.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRecipeDataSource(
    private val recipeDao: RecipeDao
) : ILocalRecipeDataSource {
    override suspend fun getRecipes(): Either<ErrorEntity, List<Recipe>> =
        try {
            val response = withContext(Dispatchers.IO) { recipeDao.getRecipes() }

            if (response.isNotEmpty()) {
                Either.Right(response.map { it.toRecipe() })
            } else {
                Either.Left(ErrorEntity.EmptyResponseError)
            }
        } catch (exception: Exception) {
            Either.Left(ErrorEntity.UnknownError(exception))
        }

    override suspend fun insertAllRecipes(recipes: List<Recipe>) =
        withContext(Dispatchers.IO) { recipeDao.insertAll(recipes.map { it.toRecipeEntity() }) }
}