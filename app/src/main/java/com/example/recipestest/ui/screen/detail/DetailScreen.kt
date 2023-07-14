package com.example.recipestest.ui.screen.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.domain.model.LocationRecipe
import com.example.recipestest.ui.screen.common.ErrorMessage
import com.example.recipestest.ui.screen.common.ArrowBackIcon
import com.example.recipestest.ui.screen.common.LoadingProgressIndicator
import com.example.recipestest.ui.theme.RecipesTestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    recipeId: String,
    onBackClick: () -> Unit,
    viewModel: RecipesDetailViewModel,
    onMapClick: (LocationRecipe) -> Unit
) {
    viewModel.getRecipeDetail(recipeId)
    RecipesTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when(val uiState = viewModel.recipeDetailState.collectAsState().value) {
                is RecipeDetailScreenState.OnError -> {
                    ErrorMessage(message = uiState.errorMessage) { viewModel.getRecipeDetail(recipeId) }
                }
                RecipeDetailScreenState.OnLoading -> {
                    LoadingProgressIndicator()
                }
                is RecipeDetailScreenState.OnRecipeFound -> {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text(text = uiState.recipeDetail.name)},
                                navigationIcon = { ArrowBackIcon(onBackClick)}
                            )
                        }
                    ) { padding ->
                        DetailRecipe(
                            recipe = uiState.recipeDetail,
                            modifier = Modifier.padding(padding),
                            onMapClick = onMapClick
                        )
                    }
                }
            }
        }
    }
}