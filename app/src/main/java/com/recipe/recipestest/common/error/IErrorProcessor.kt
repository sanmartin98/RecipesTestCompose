package com.recipe.recipestest.common.error

import com.recipe.common.ErrorEntity

interface IErrorProcessor {
    fun getErrorMessage(error: ErrorEntity): String
}