package com.recipe.recipestest.ui.screen.detail

import com.recipe.domain.model.RecipeDetail

sealed class RecipeDetailScreenState {
    data class OnRecipeFound(val recipeDetail: RecipeDetail): RecipeDetailScreenState()
    object OnLoading : RecipeDetailScreenState()
    data class OnError(val errorMessage: String) : RecipeDetailScreenState()
}
