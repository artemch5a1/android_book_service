package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class GetAllBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke() : CustomResult<List<Book>> {

        try {
            val response = bookRepository.getAllBooks()

            return CustomResult.Success(response)
        }
        catch (ex: Exception){

            return CustomResult.Failure(ex.message ?: "")

        }

    }

}