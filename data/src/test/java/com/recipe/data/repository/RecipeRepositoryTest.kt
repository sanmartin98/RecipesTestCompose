package com.recipe.data.repository

import com.recipe.common.Either
import com.recipe.common.ErrorEntity
import com.recipe.data.createRecipe
import com.recipe.data.createRecipeDetail
import com.recipe.data.datasource.local.LocalRecipeDataSource
import com.recipe.data.datasource.remote.RemoteRecipeDataSource
import com.recipe.data.network.NetworkManager
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test class of [RecipeRepository]
 */
@ExperimentalCoroutinesApi
class RecipeRepositoryTest {

    @MockK
    private lateinit var remoteRecipeDataSource: RemoteRecipeDataSource

    @MockK
    private lateinit var localRecipeDataSource: LocalRecipeDataSource

    @MockK
    private lateinit var networkManager: NetworkManager

    private lateinit var recipeRepository: RecipeRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        recipeRepository = RecipeRepository(
            remoteRecipeDataSource,
            localRecipeDataSource,
            networkManager
        )
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun `Given a call to getRecipes When this answer is correct Then it gives us a List of Recipe model`() =
        runTest {
            //Given
            val recipeList = listOf(createRecipe())

            coEvery { remoteRecipeDataSource.getRecipes() } answers { Either.Right(recipeList) }
            coEvery { networkManager.isConnected() } answers { true }

            //When
            val result = recipeRepository.getRecipes()

            //Then
            assert(result is Either.Right)
            Assert.assertEquals((result as Either.Right).value, recipeList)
        }

    @Test
    fun `Given a call to getRecipes When the user has internet issues but has info in the data base Then it gives us a List of Recipe model`() =
        runTest {
            //Given
            val recipeList = listOf(createRecipe())

            coEvery { localRecipeDataSource.getRecipes() } answers { Either.Right(recipeList) }
            coEvery { networkManager.isConnected() } answers { false }

            //When
            val result = recipeRepository.getRecipes()

            //Then
            assert(result is Either.Right)
            Assert.assertEquals((result as Either.Right).value, recipeList)
        }

    @Test
    fun `Given a call to getRecipes When the user has internet issues Then it gives us an internet error`() =
        runTest {
            //Given
            val internetError = ErrorEntity.NetworkError(102)

            coEvery { remoteRecipeDataSource.getRecipes() } answers { Either.Left(internetError) }
            coEvery { networkManager.isConnected() } answers { true }

            //When
            val result = recipeRepository.getRecipes()

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.NetworkError)
            Assert.assertEquals(result.error, internetError)
        }

    @Test
    fun `Given a call to getRecipes When the answer is empty Then it gives us an empty error`() =
        runTest {
            //Given
            val emptyError = ErrorEntity.EmptyResponseError

            coEvery { remoteRecipeDataSource.getRecipes() } answers { Either.Left(emptyError) }
            coEvery { networkManager.isConnected() } answers { true }

            //When
            val result = recipeRepository.getRecipes()

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.EmptyResponseError)
            Assert.assertEquals(result.error, emptyError)
        }

    @Test
    fun `Given a call to getRecipeDetail when this answer is correct Then it gives us a RecipeDetail model`() =
        runTest {
            //Given
            val recipeId = "1"
            val recipeDetail = createRecipeDetail()

            coEvery { remoteRecipeDataSource.getRecipeDetail(recipeId) } answers { Either.Right(recipeDetail) }

            //When
            val result = recipeRepository.getRecipeDetail(recipeId)

            //Then
            assert(result is Either.Right)
            Assert.assertEquals((result as Either.Right).value, recipeDetail)
        }

    @Test
    fun `Given a call to getRecipeDetail When the user has internet issues Then it gives us an internet error`() =
        runTest {
            //Given
            val recipeId = "1"
            val internetError = ErrorEntity.NetworkError(102)

            coEvery { remoteRecipeDataSource.getRecipeDetail(recipeId) } answers { Either.Left(internetError) }

            //When
            val result = recipeRepository.getRecipeDetail(recipeId)

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.NetworkError)
            Assert.assertEquals(result.error, internetError)
        }

    @Test
    fun `Given a call to getRecipeDetail When the answer is empty Then it gives us an empty error`() =
        runTest {
            //Given
            val recipeId = "1"
            val emptyError = ErrorEntity.EmptyResponseError

            coEvery { remoteRecipeDataSource.getRecipeDetail(recipeId) } answers { Either.Left(emptyError) }

            //When
            val result = recipeRepository.getRecipeDetail(recipeId)

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.EmptyResponseError)
            Assert.assertEquals(result.error, emptyError)
        }
}