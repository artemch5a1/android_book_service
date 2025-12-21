package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Book

interface BookRepository {

    suspend fun getAllBooks() : List<Book>

}