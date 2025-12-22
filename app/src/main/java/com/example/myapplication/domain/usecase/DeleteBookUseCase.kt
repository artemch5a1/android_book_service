package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.repository.BookRepository
import javax.inject.Inject

class DeleteBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {

    suspend operator fun invoke(id: String) : CustomResult<Unit> {

        try {
            bookRepository.deleteBook(id)

            return CustomResult.Success(Unit)
        }
        catch (ex: Exception){
            return CustomResult.Failure(ex.message ?: "")
        }

    }

}