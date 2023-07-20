package com.recipe.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecipeResponseDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url_image")
    val urlImage: String,
    @SerializedName("ingredients")
    val ingredients: List<String>
)