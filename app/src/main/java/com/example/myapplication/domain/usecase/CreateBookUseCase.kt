package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Book
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.repository.BookRepository
import java.lang.Exception
import javax.inject.Inject

class CreateBookUseCase @Inject constructor(
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