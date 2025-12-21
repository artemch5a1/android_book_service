package com.example.myapplication.domain.model

import java.time.OffsetDateTime

data class Book(
    val id: String,
    val name: String,
    val description: String,
    val datePublish: OffsetDateTime,
    val author: String,
    val category: String
)
