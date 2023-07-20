package com.recipe.recipestest.common.error

import android.content.Context
import com.recipe.recipestest.R

class NetworkErrorInterpreter(private val context: Context): INetworkErrorInterpreter {

    companion object {
        const val NO_INTERNET_CONNECTION = 102
        const val UNAUTHORIZED = 401
        const val RECIPE_NOT_FOUND = 404
    }

    override fun interpret(status: Int): String =
        when (status) {
            NO_INTERNET_CONNECTION -> context.getString(R.string.recipe_internet_error)
            UNAUTHORIZED -> context.getString(R.string.recipe_error_unauthorized)
            RECIPE_NOT_FOUND -> context.getString(R.string.recipe_not_found)
            else -> context.getString(R.string.recipe_error_unknown)
        }

}