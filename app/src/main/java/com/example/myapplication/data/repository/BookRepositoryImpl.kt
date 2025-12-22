package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.BookMapper
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BookRepository {


    override suspend fun getAllBooks(): List<Book> {
        return BookMapper.toListDomain(
            apiService.getAllBook()
        )
    }

    override suspend fun getBook(id: String): Book {
        return BookMapper.toDomain(apiService.getAllBook(id))
    }

    override suspend fun createBook(book: Book) {
        return apiService.createBook(BookMapper.toCreateDto(book))
    }

    override suspend fun updateBook(book: Book) {
        return apiService.updateBook(book.id, BookMapper.toCreateDto(book))
    }

    override suspend fun deleteBook(id: String) {
        apiService.deleteBook(id)
    }


}