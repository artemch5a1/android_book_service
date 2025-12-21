package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.ApiCollection
import com.example.myapplication.data.dto.CategoryDto
import com.example.myapplication.domain.model.Category

object CategoryMapper {

    fun toDomain(categoryDto: CategoryDto) : Category{

        return Category(
            id = categoryDto.id,
            categoryTitle = categoryDto.categoryTitle
        )

    }

    fun toListDomain(categoryDto: ApiCollection<CategoryDto>) : List<Category> {

        return categoryDto.items.map { categoryDto -> toDomain(categoryDto) }

    }
}