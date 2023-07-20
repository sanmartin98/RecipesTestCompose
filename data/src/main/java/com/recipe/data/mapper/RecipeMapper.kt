package com.recipe.data.mapper

import com.recipe.data.local.model.RecipeEntity
import com.recipe.data.remote.response.LocationResponseDTO
import com.recipe.data.remote.response.RecipeDetailResponseDto
import com.recipe.data.remote.response.RecipeResponseDto
import com.recipe.domain.model.LocationRecipe
import com.recipe.domain.model.Recipe
import com.recipe.domain.model.RecipeDetail

fun RecipeResponseDto.toRecipe() = Recipe(id, name, urlImage, ingredients)

fun RecipeDetailResponseDto.toRecipeDetail() =
    RecipeDetail(id, name, urlImage, description, ingredients, location.toLocationRecipe())

fun LocationResponseDTO.toLocationRecipe() = LocationRecipe(lat, lng, locationName)

fun RecipeEntity.toRecipe() = Recipe(id, name, urlImage, ingredients)

fun Recipe.toRecipeEntity() = RecipeEntity(id, name, urlImage, ingredients)