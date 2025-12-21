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


}