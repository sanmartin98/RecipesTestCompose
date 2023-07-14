package com.example.recipestest.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Recipe
import com.example.domain.usecase.GetRecipesUseCase
import com.example.recipestest.common.error.IErrorProcessor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val errorProcessor: IErrorProcessor
): ViewModel() {
    private val _recipesState = MutableStateFlow<RecipeScreenState>(RecipeScreenState.OnLoading)
    val recipesState = _recipesState.asStateFlow()

    init {
        getRecipes()
    }

    fun getRecipes() {
        _recipesState.value = RecipeScreenState.OnLoading
        viewModelScope.launch {
            getRecipesUseCase.getRecipes().fold(
                functionLeft = {
                    _recipesState.value =
                        RecipeScreenState.OnError(errorProcessor.getErrorMessage(it))
                }, functionRight = {
                    _recipesState.value = RecipeScreenState.OnRecipesLoaded(it)
                }
            )
        }
    }

    fun searchRecipes(query: String): List<Recipe> {
        return if (query.isNotEmpty()) {
            (_recipesState.value as RecipeScreenState.OnRecipesLoaded).recipes.filter {
                it.name.lowercase().contains(query.lowercase()) ||
                        it.ingredients.joinToString(",").lowercase().contains(query.lowercase())
            }
        } else {
            (_recipesState.value as RecipeScreenState.OnRecipesLoaded).recipes
        }
    }
}