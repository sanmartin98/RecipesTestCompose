package com.example.domain.usecase

import com.example.domain.repository.IRecipeRepository

class GetRecipesUseCase(
    private val iRecipeRepository: IRecipeRepository
) {
    suspend fun getRecipes() = iRecipeRepository.getRecipes()
}