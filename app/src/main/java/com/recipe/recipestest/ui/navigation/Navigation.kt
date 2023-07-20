package com.recipe.recipestest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.recipe.domain.model.LocationRecipe
import com.recipe.recipestest.ui.screen.detail.DetailScreen
import com.recipe.recipestest.ui.screen.main.MainScreen
import com.recipe.recipestest.ui.screen.map.MapScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main.route) {
            MainScreen(recipesViewModel = hiltViewModel()) { recipe ->
                navController.navigate(NavItem.Detail.createNavRoute(recipe.id))
            }
        }
        composable(
            route = NavItem.Detail.route,
            arguments = NavItem.Detail.args
        ) { backStackEntry ->
            DetailScreen(
                recipeId = backStackEntry.findArg(NavArg.RecipeId.key),
                onBackClick = { navController.popBackStack() },
                viewModel = hiltViewModel(),
                onMapClick = { location ->
                    navController.navigate((NavItem.Map.createRoute(location.lat, location.lng, location.locationName)))
                }
            )
        }
        composable(NavItem.Map.route) { backStackEntry ->
            MapScreen(
                locationRecipe = LocationRecipe(
                    backStackEntry.findArg(NavArg.RecipeLat.key),
                    backStackEntry.findArg(NavArg.RecipeLng.key),
                    backStackEntry.findArg(NavArg.RecipeLocationName.key)
                )
            )
        }
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(key: String): T {
    val value = arguments?.get(key)
    requireNotNull(value)
    return value as T
}