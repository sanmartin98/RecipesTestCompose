package com.example.data.remote.service

import com.example.data.commons.Urls
import com.example.data.remote.response.RecipeDetailResponseDto
import com.example.data.remote.response.RecipeResponseDto
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