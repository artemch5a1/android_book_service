package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {

    suspend operator fun invoke() : CustomResult<List<Category>> {

        try {
            val response = categoryRepository.getAllCategory()

            return CustomResult.Success(response)
        }
        catch (ex: Exception){

            return CustomResult.Failure(ex.message ?: "")

        }

    }

}