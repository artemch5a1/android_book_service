package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.ApiCollection
import com.example.myapplication.data.dto.BookDto
import com.example.myapplication.domain.model.Book
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object BookMapper {

    fun toDomain(bookDto: BookDto) : Book {

        val pattern = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss.SSSX")

        return Book(
            id = bookDto.id,
            name = bookDto.name,
            description = bookDto.description,
            author = bookDto.author,
            category = bookDto.category,
            datePublish = OffsetDateTime.parse(bookDto.datePublish, pattern)
        )

    }

    fun toListDomain(booksDto: ApiCollection<BookDto>) : List<Book>{

        return booksDto.items.map { booksDto -> toDomain(booksDto) }

    }

}