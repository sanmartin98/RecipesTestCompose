package com.example.recipestest.common.error

import android.content.Context
import com.example.common.ErrorEntity
import com.example.recipestest.R

class ErrorProcessor(
    private val context: Context,
    private val networkErrorInterpreter: INetworkErrorInterpreter
): IErrorProcessor {
    override fun getErrorMessage(error: ErrorEntity): String {
        return when(error) {
            ErrorEntity.EmptyResponseError -> context.getString(R.string.recipe_error_no_response)
            is ErrorEntity.NetworkError -> networkErrorInterpreter.interpret(error.httpStatus)
            is ErrorEntity.UnknownError -> context.getString(R.string.recipe_error_unknown)
        }
    }
}