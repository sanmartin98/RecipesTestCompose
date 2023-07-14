package com.example.data.datasource

import com.example.common.Either
import com.example.common.ErrorEntity
import com.example.data.createRecipeEntity
import com.example.data.datasource.local.LocalRecipeDataSource
import com.example.data.local.dao.RecipeDao
import com.example.data.mapper.toRecipe
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Test class of [LocalRecipeDataSource]
 */
@ExperimentalCoroutinesApi
class LocalRecipeDataSourceTest {

    @RelaxedMockK
    private lateinit var recipeDao: RecipeDao

    private lateinit var localRecipeDataSource: LocalRecipeDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        localRecipeDataSource = LocalRecipeDataSource(recipeDao)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given a call to getRecipes when this answer is correct Then it gives us a List of Recipe model`() =
        runTest {
            //Given
            val recipeEntity = createRecipeEntity()
            val recipe = recipeEntity.toRecipe()

            coEvery { recipeDao.getRecipes() } answers { listOf(recipeEntity) }

            //When
            val result = localRecipeDataSource.getRecipes()

            //Then
            assert(result is Either.Right)
            Assert.assertEquals((result as Either.Right).value.first(), recipe)
        }

    @Test
    fun `Given a call to getRecipes When the answer is failure Then it gives us an unknown error`() =
        runTest {
            //Given
            coEvery { recipeDao.getRecipes() } throws Exception()

            //When
            val result = localRecipeDataSource.getRecipes()

            //Then
            assert(result is Either.Left)
            assert((result as Either.Left).error is ErrorEntity.UnknownError)
        }
}