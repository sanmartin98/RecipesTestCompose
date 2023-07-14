package com.example.domain.model

data class Recipe(
    val id: String,
    val name: String,
    val urlImage: String,
    val ingredients: List<String>
)