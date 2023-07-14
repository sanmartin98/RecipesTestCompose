package com.example.data

import com.example.data.remote.response.LocationResponseDTO
import com.example.data.remote.response.RecipeDetailResponseDto
import com.example.data.remote.response.RecipeResponseDto
import com.example.domain.model.LocationRecipe
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail

fun createRecipeResponseDto() = RecipeResponseDto(
    id = "1",
    name = "Lasagna",
    urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyuBYhIQBWTVv3n97oaSZdxFlmXrTdVls_TMD0UMSqffUdPc-W26mNimFqQmiOjKHfspI&usqp=CAU",
    ingredients = listOf("meat, cheese")
)

fun createRecipe() = Recipe(
    id = "1",
    name = "Lasagna",
    urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyuBYhIQBWTVv3n97oaSZdxFlmXrTdVls_TMD0UMSqffUdPc-W26mNimFqQmiOjKHfspI&usqp=CAU",
    ingredients = listOf("meat, cheese")
)

fun createRecipeDetailResponseDto() = RecipeDetailResponseDto(
    id = "1",
    name = "Lasagna",
    urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyuBYhIQBWTVv3n97oaSZdxFlmXrTdVls_TMD0UMSqffUdPc-W26mNimFqQmiOjKHfspI&usqp=CAU",
    description = "Delicious",
    ingredients = listOf("meat, cheese"),
    location = createLocationResponseDto()
)

fun createLocationResponseDto() = LocationResponseDTO(
    lat = "41.902782",
    lng = "12.496366",
    locationName = "Rome"
)

fun createRecipeDetail() = RecipeDetail(
    id = "1",
    name = "Lasagna",
    urlImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyuBYhIQBWTVv3n97oaSZdxFlmXrTdVls_TMD0UMSqffUdPc-W26mNimFqQmiOjKHfspI&usqp=CAU",
    description = "Delicious",
    ingredients = listOf("meat, cheese"),
    location = createLocation()
)

fun createLocation() = LocationRecipe(
    lat = "41.902782",
    lng = "12.496366",
    locationName = "Rome"
)