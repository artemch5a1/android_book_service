package com.example.myapplication.data.dto

data class CreateBookDto(
    val name: String,
    val description: String,
    val datePublish: String,
    val author: String,
    val category: String
)