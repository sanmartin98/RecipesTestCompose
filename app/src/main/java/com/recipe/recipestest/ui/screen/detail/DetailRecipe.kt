package com.recipe.recipestest.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.recipe.domain.model.LocationRecipe
import com.recipe.domain.model.RecipeDetail
import com.recipe.recipestest.R

@Composable
fun DetailRecipe(
    recipe: RecipeDetail,
    modifier: Modifier = Modifier,
    onMapClick: (LocationRecipe) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailRecipeImage(urlImage = recipe.urlImage)
        DetailRecipeText(text = recipe.description)
        DetailRecipeText(
            text = "${stringResource(R.string.ingredients_title)} ${recipe.ingredients.joinToString(", ")}"
        )
        LocationCard(
            location = recipe.location.locationName
        ) {
            onMapClick(recipe.location)
        }
    }
}

@Composable
fun DetailRecipeImage(
    urlImage: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(dimensionResource(R.dimen.image_recipe_detail_height))
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
fun DetailRecipeText(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(R.dimen.padding_small),
                horizontal = dimensionResource(R.dimen.padding_medium)
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
    }
}

@Composable
fun LocationCard(location: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(dimensionResource(R.dimen.padding_medium)),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = dimensionResource(R.dimen.padding_small),
                horizontal = dimensionResource(R.dimen.padding_medium)
            )
        ) {
            Text(
                text = location,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = location,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}