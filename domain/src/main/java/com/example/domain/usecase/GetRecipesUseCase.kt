package com.example.domain.usecase

import com.example.domain.repository.IRecipeRepository
import javax.inject.Inject

class GetRecipesUseCase(
    private val iRecipeRepository: IRecipeRepository
) {
    suspend fun getRecipes() = iRecipeRepository.getRecipes()
}