package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Book

interface BookRepository {

    suspend fun getAllBooks() : List<Book>

    suspend fun getBook(id: String) : Book

    suspend fun createBook(book: Book)

    suspend fun updateBook(book: Book)

    suspend fun deleteBook(id: String)
}