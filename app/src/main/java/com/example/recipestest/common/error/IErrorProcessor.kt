package com.example.recipestest.common.error

import com.example.common.ErrorEntity

interface IErrorProcessor {
    fun getErrorMessage(error: ErrorEntity): String
}