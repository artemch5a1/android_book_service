package com.example.myapplication.domain.model

data class LoginResponse(
    val token: String,
    val loginDetail: LoginDetail
)

data class LoginDetail(
    val id: String
)