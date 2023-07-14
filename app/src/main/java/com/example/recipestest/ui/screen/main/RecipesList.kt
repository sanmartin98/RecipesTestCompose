package com.example.recipestest.ui.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.Recipe
import com.example.recipestest.R

@Composable
fun RecipeList(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_xsmall)),
        modifier = modifier,
        state = rememberLazyListState()
    ) {
        items(items = recipes) {recipe ->
            RecipesListItem(
                recipe = recipe,
                onClick = { onRecipeClick(recipe) },
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_xsmall))
            )
        }
    }
}

@Composable
fun RecipesListItem(
    recipe: Recipe,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column {
            RecipeImage(urlImage = recipe.urlImage)
            Title(title = recipe.name)
            RecipesIngredientsText(ingredients = recipe.ingredients)
        }
    }
}

@Composable
fun RecipeImage(
    urlImage: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(dimensionResource(R.dimen.image_recipe_height))
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = urlImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun Title(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
    }
}

@Composable
private fun RecipesIngredientsText(ingredients: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
                bottom = dimensionResource(R.dimen.padding_small)
            )
            //.padding(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = ingredients.joinToString(", "),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}