package com.example.myapplication.data.dto

data class LoginResponseDto(
    val token: String,
    val record: LoginDetailDto
)

data class LoginDetailDto(
    val id: String
)


