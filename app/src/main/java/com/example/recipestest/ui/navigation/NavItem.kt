package com.example.recipestest.ui.navigation
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) {type = it.navType}
    }

    object Main : NavItem("main")
    object Detail : NavItem("detail", listOf(NavArg.RecipeId)) {
        fun createNavRoute(recipeId: String) = "$baseRoute/$recipeId"
    }
    object Map : NavItem("map", listOf(NavArg.RecipeLat, NavArg.RecipeLng, NavArg.RecipeLocationName)) {
        fun createRoute(lat: String, lng: String, recipeLocationName: String) = "$baseRoute/$lat/$lng/$recipeLocationName"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    RecipeId("recipeId", NavType.StringType),
    RecipeLat("lat", NavType.StringType),
    RecipeLng("lng", NavType.StringType),
    RecipeLocationName("recipeLocationName", NavType.StringType)
}