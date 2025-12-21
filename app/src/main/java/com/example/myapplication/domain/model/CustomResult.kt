package com.example.myapplication.domain.model

sealed class CustomResult<out T> {

    data class Success<out T>(val value:T) : CustomResult<T>()

    data class Failure(val message: String) : CustomResult<Nothing>()
}