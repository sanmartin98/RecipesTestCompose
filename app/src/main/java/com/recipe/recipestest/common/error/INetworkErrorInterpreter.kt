package com.recipe.recipestest.common.error

interface INetworkErrorInterpreter {
    fun interpret(status: Int): String
}