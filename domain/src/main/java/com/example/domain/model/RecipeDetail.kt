package com.example.domain.model

data class RecipeDetail(
    val id: String,
    val name: String,
    val urlImage: String,
    val description: String,
    val ingredients: List<String>,
    val location: LocationRecipe
)
