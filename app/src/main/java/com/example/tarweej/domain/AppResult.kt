package com.example.tarweej.domain

sealed class AppResult<T> {
    class Success<T>(val data: T) : AppResult<T>()
    class Failure<T>(var errorMessage: String, var errorCode: Int? = null) : AppResult<T>()
}