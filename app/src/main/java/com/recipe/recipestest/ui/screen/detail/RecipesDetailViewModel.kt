package com.recipe.recipestest.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.recipe.domain.usecase.GetRecipeDetailUseCase
import com.recipe.recipestest.common.error.IErrorProcessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesDetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase,
    private val errorProcessor: IErrorProcessor
): ViewModel() {
    private val _recipeDetailState = MutableStateFlow<RecipeDetailScreenState>(RecipeDetailScreenState.OnLoading)
    val recipeDetailState = _recipeDetailState

    fun getRecipeDetail(recipeId: String) {
        _recipeDetailState.value = RecipeDetailScreenState.OnLoading
        viewModelScope.launch {
            getRecipeDetailUseCase.getRecipeDetail(recipeId = recipeId).fold(
                functionLeft = {
                    _recipeDetailState.value =
                        RecipeDetailScreenState.OnError(errorProcessor.getErrorMessage(it))
                }, functionRight = {
                    _recipeDetailState.value = RecipeDetailScreenState.OnRecipeFound(it)
                }
            )
        }
    }
}