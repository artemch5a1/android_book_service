package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.CategoryMapper
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.domain.model.Category
import com.example.myapplication.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CategoryRepository {

    override suspend fun getAllCategory(): List<Category> {
        return CategoryMapper.toListDomain(
            apiService.getAllCategory()
        )
    }

}