package com.recipe.domain.usecase

import com.recipe.domain.repository.IRecipeRepository

class GetRecipeDetailUseCase(
    private val iRecipeRepository: IRecipeRepository
) {
    suspend fun getRecipeDetail(recipeId: String) = iRecipeRepository.getRecipeDetail(recipeId)
}