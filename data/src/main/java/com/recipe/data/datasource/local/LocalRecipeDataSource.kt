package com.recipe.data.datasource.local

import com.recipe.common.Either
import com.recipe.common.ErrorEntity
import com.recipe.data.local.dao.RecipeDao
import com.recipe.data.mapper.toRecipe
import com.recipe.data.mapper.toRecipeEntity
import com.recipe.domain.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Test class [LocalRecipeDataSourceTest]
 */
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