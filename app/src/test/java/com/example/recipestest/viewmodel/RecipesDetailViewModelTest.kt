package com.example.recipestest.viewmodel

import app.cash.turbine.test
import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.domain.usecase.GetRecipeDetailUseCase
import com.example.recipestest.CoroutineTestRule
import com.example.recipestest.common.error.IErrorProcessor
import com.example.recipestest.createRecipeDetail
import com.example.recipestest.ui.screen.detail.RecipeDetailScreenState
import com.example.recipestest.ui.screen.detail.RecipesDetailViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RecipesDetailViewModelTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var getRecipeDetailUseCase: GetRecipeDetailUseCase

    @MockK
    private lateinit var errorProcessor: IErrorProcessor

    private lateinit var recipesDetailViewModel: RecipesDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        recipesDetailViewModel = RecipesDetailViewModel(
            getRecipeDetailUseCase,
            errorProcessor
        )
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun `Given a call to getRecipeDetail When this answer is success Then it changes recipeDetailState to OnRecipeFound`() =
        coroutineTestRule.runBlockingTest {
            //Given
            val recipeId = "1"
            val recipeDetail = createRecipeDetail()

            coEvery { getRecipeDetailUseCase.getRecipeDetail(any()) } answers { Either.Right(recipeDetail) }

            //When
            recipesDetailViewModel.getRecipeDetail(recipeId)

            //Then
            recipesDetailViewModel.recipeDetailState.test {
                val result: RecipeDetailScreenState = awaitItem()
                assert(result is RecipeDetailScreenState.OnRecipeFound)
                assertEquals((result as RecipeDetailScreenState.OnRecipeFound).recipeDetail, recipeDetail)
                cancelAndConsumeRemainingEvents()
            }
            coVerify(exactly = 1) { getRecipeDetailUseCase.getRecipeDetail(recipeId) }
        }

    @Test
    fun `Given a call to getRecipeDetail When the user has internet issues Then it gives us an internet error`() =
        coroutineTestRule.runBlockingTest {
            //Given
            val recipeId = "1"
            val internetError = ErrorEntity.NetworkError(102)
            val errorMessage = "Please review your internet connection."

            coEvery { getRecipeDetailUseCase.getRecipeDetail(recipeId) } answers { Either.Left(internetError) }
            coEvery { errorProcessor.getErrorMessage(any()) } answers { errorMessage }

            //When
            recipesDetailViewModel.getRecipeDetail(recipeId)

            //Then
            recipesDetailViewModel.recipeDetailState.test {
                val result: RecipeDetailScreenState = awaitItem()
                assert(result is RecipeDetailScreenState.OnError)
                assertEquals((result as RecipeDetailScreenState.OnError).errorMessage, errorMessage)
                cancelAndConsumeRemainingEvents()
            }
            coVerify(exactly = 1) { getRecipeDetailUseCase.getRecipeDetail(recipeId) }
        }
}