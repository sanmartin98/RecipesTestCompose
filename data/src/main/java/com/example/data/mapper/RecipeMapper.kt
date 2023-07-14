package com.example.data.mapper

import com.example.data.local.model.RecipeEntity
import com.example.data.remote.response.LocationResponseDTO
import com.example.data.remote.response.RecipeDetailResponseDto
import com.example.data.remote.response.RecipeResponseDto
import com.example.domain.model.LocationRecipe
import com.example.domain.model.Recipe
import com.example.domain.model.RecipeDetail

fun RecipeResponseDto.toRecipe() = Recipe(id, name, urlImage, ingredients)

fun RecipeDetailResponseDto.toRecipeDetail() =
    RecipeDetail(id, name, urlImage, description, ingredients, location.toLocationRecipe())

fun LocationResponseDTO.toLocationRecipe() = LocationRecipe(lat, lng, locationName)

fun RecipeEntity.toRecipe() = Recipe(id, name, urlImage, ingredients)

fun Recipe.toRecipeEntity() = RecipeEntity(id, name, urlImage, ingredients)