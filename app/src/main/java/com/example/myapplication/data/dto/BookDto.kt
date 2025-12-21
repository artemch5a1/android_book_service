package com.example.myapplication.data.dto

import java.time.OffsetDateTime

data class BookDto(
    val id: String,
    val name: String,
    val description: String,
    val datePublish: String,
    val author: String,
    val category: String
)
