package com.example.recipestest.common.error

interface INetworkErrorInterpreter {
    fun interpret(status: Int): String
}