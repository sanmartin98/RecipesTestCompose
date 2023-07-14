package com.example.recipestest.ui.screen.detail

import com.example.domain.model.RecipeDetail

sealed class RecipeDetailScreenState {
    data class OnRecipeFound(val recipeDetail: RecipeDetail): RecipeDetailScreenState()
    object OnLoading : RecipeDetailScreenState()
    data class OnError(val errorMessage: String) : RecipeDetailScreenState()
}
