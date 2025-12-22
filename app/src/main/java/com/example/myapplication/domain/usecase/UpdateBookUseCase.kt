package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class UpdateBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(book: Book) : CustomResult<Unit> {

        try {

            bookRepository.createBook(book)

            return CustomResult.Success(Unit)
        }
        catch (ex: Exception){

            return CustomResult.Failure(ex.message ?: "")

        }

    }

}