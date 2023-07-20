package com.recipe.data.remote.service

import com.recipe.data.commons.Urls
import com.recipe.data.remote.response.RecipeDetailResponseDto
import com.recipe.data.remote.response.RecipeResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService {
    @GET(Urls.getRecipes)
    suspend fun getRecipes(): Response<List<RecipeResponseDto>>

    @GET(Urls.getRecipeDetail)
    suspend fun getRecipeDetail(
        @Path("recipeId") recipeId: String
    ): Response<RecipeDetailResponseDto>
}