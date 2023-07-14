package com.example.data.datasource

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.data.createRecipeDetailResponseDto
import com.example.data.createRecipeResponseDto
import com.example.data.datasource.remote.RemoteRecipeDataSource
import com.example.data.mapper.toRecipe
import com.example.data.mapper.toRecipeDetail
import com.example.data.remote.service.RecipeService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Test class of [RemoteRecipeDataSource]
 */
@ExperimentalCoroutinesApi
class RemoteRecipeDataSourceTest {

    @MockK
    private lateinit var recipeService: RecipeService

    private lateinit var remoteRecipeDataSource: RemoteRecipeDataSource

    @Before
    fun seyUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        remoteRecipeDataSource = RemoteRecipeDataSource(recipeService)
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun `Given a call to getRecipes when this answer is correct Then it gives us a List of Recipe model`() =
        runTest {
            //Given
            val recipeResponseDto = createRecipeResponseDto()
            val recipe = recipeResponseDto.toRecipe()

            coEvery { recipeService.getRecipes() } answers {
                Response.success(
                    listOf(
                        recipeResponseDto
                    )
                )
            }

            //When
            val result = remoteRecipeDataSource.getRecipes()

            //Then
            assert(result is Either.Right)
            Assert.assertEquals((result as Either.Right).value.first(), recipe)
        }

    @Test
    fun `Given a call to getRecipes When the answer is failure Then it gives us an unknown error`() =
        runTest {
            //Given
            coEvery { recipeService.getRecipes() } answers {
                Response.error(
                    100,
                    "{\"key\":[\"somestuff\"]}".toResponseBody("application/json".toMediaTypeOrNull())
                )
            }

            //When
            val result = remoteRecipeDataSource.getRecipes()

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.UnknownError)
        }

    @Test
    fun `Given a call to getRecipeDetail when this answer is correct Then it gives us a RecipeDetail model`() =
        runTest {
            //Given
            val recipeId = "1"
            val recipeDetailResponseDto = createRecipeDetailResponseDto()
            val recipeDetail = recipeDetailResponseDto.toRecipeDetail()

            coEvery { recipeService.getRecipeDetail(recipeId) } answers {
                Response.success(
                    recipeDetailResponseDto
                )
            }

            //When
            val result = remoteRecipeDataSource.getRecipeDetail(recipeId)

            //Verify
            assert(result is Either.Right)
            Assert.assertEquals((result as Either.Right).value, recipeDetail)
        }

    @Test
    fun `Given a call to getRecipeDetail When the answer is failure Then it gives us an unknown error`() =
        runTest {
            //Given
            val recipeId = "1"

            coEvery { recipeService.getRecipeDetail(recipeId) } answers {
                Response.error(
                    100,
                    "{\"key\":[\"somestuff\"]}".toResponseBody("application/json".toMediaTypeOrNull())
                )
            }

            //When
            val result = remoteRecipeDataSource.getRecipeDetail(recipeId)

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.UnknownError)
        }
}