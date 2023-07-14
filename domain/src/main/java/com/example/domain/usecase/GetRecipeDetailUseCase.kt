package com.example.domain.usecase

import com.example.domain.repository.IRecipeRepository

class GetRecipeDetailUseCase(
    private val iRecipeRepository: IRecipeRepository
) {
    suspend fun getRecipeDetail(recipeId: String) = iRecipeRepository.getRecipeDetail(recipeId)
}