package com.example.recipestest.viewmodel

import app.cash.turbine.test
import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.domain.usecase.GetRecipesUseCase
import com.example.recipestest.CoroutineTestRule
import com.example.recipestest.common.error.ErrorProcessor
import com.example.recipestest.createRecipe
import com.example.recipestest.ui.screen.main.RecipeScreenState
import com.example.recipestest.ui.screen.main.RecipesViewModel
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
class RecipesViewModelTest {

    @get:Rule
    val coroutineTestRule: CoroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @MockK
    private lateinit var errorProcessor: ErrorProcessor

    private lateinit var recipesViewModel: RecipesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun `Given an init RecipesViewModel When this answer is success Then it changes recipesState to OnRecipesLoaded`() =
        coroutineTestRule.runBlockingTest {
            //Given
            val recipeList = listOf(createRecipe())

            coEvery { getRecipesUseCase.getRecipes() } answers { Either.Right(recipeList) }

            //When
            recipesViewModel = RecipesViewModel(getRecipesUseCase, errorProcessor)

            //Then
            recipesViewModel.recipesState.test {
                val result: RecipeScreenState = awaitItem()
                assert(result is RecipeScreenState.OnRecipesLoaded)
                assertEquals((result as RecipeScreenState.OnRecipesLoaded).recipes, recipeList)
                cancelAndConsumeRemainingEvents()
            }
            coVerify(exactly = 1) { getRecipesUseCase.getRecipes() }
        }

    @Test
    fun `Given an init RecipesViewModel When the user has internet issues Then it gives us an internet error`() =
        coroutineTestRule.runBlockingTest {
            //Given
            val internetError = ErrorEntity.NetworkError(102)
            val errorMessage = "Please review your internet connection."

            coEvery { getRecipesUseCase.getRecipes() } answers { Either.Left(internetError) }
            coEvery { errorProcessor.getErrorMessage(any()) } answers { errorMessage }

            //When
            recipesViewModel = RecipesViewModel(getRecipesUseCase, errorProcessor)

            //Then
            recipesViewModel.recipesState.test {
                val result: RecipeScreenState = awaitItem()
                assert(result is RecipeScreenState.OnError)
                assertEquals((result as RecipeScreenState.OnError).errorMessage, errorMessage)
                cancelAndConsumeRemainingEvents()
            }
            coVerify(exactly = 1) { getRecipesUseCase.getRecipes() }
        }
}