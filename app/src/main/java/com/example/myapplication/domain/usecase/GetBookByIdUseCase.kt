package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class GetBookByIdUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(id: String) : CustomResult<Book> {

        try {
            val response = bookRepository.getBook(id)

            return CustomResult.Success(response)
        }
        catch (ex: Exception){

            return CustomResult.Failure(ex.message ?: "")

        }

    }

}