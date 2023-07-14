package com.example.recipestest.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.example.domain.model.Recipe
import com.example.recipestest.ui.screen.common.ErrorMessage
import com.example.recipestest.ui.screen.common.LoadingProgressIndicator
import com.example.recipestest.ui.screen.common.SearchView
import com.example.recipestest.ui.theme.RecipesTestTheme
import com.example.recipestest.R

@Composable
fun MainScreen(
    recipesViewModel: RecipesViewModel,
    onRecipeClick: (Recipe) -> Unit
) {
    val query = remember { mutableStateOf(TextFieldValue("")) }
    val uiState = recipesViewModel.recipesState.collectAsState().value

    RecipesTestTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            when (uiState) {
                is RecipeScreenState.OnError -> {
                    ErrorMessage(message = uiState.errorMessage) { recipesViewModel.getRecipes() }
                }

                RecipeScreenState.OnLoading -> {
                    LoadingProgressIndicator()
                }

                is RecipeScreenState.OnRecipesLoaded -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SearchView(
                            state = query,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(dimensionResource(R.dimen.padding_small)),
                            placeholder = stringResource(R.string.search)
                        )
                        RecipeList(
                            recipes = recipesViewModel.searchRecipes(query.value.text),
                            onRecipeClick = onRecipeClick,
                            modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                        )
                    }
                }
            }

        }
    }
}