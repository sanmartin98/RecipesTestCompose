package com.recipe.domain.usecase

import com.recipe.domain.repository.IRecipeRepository

class GetRecipesUseCase(
    private val iRecipeRepository: IRecipeRepository
) {
    suspend fun getRecipes() = iRecipeRepository.getRecipes()
}