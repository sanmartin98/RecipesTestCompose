package com.recipe.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url_image")
    val urlImage: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ingredients")
    val ingredients: List<String>,
    @SerializedName("location")
    val location: LocationResponseDTO
)
