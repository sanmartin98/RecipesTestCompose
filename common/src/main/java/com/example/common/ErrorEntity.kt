package com.example.common

sealed interface ErrorEntity {
    data class NetworkError(val httpStatus: Int) : ErrorEntity
    object EmptyResponseError : ErrorEntity
    data class UnknownError(val exception: Exception) : ErrorEntity
}