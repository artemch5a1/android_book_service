package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.ApiCollection
import com.example.myapplication.data.dto.BookDto
import com.example.myapplication.data.dto.CategoryDto
import com.example.myapplication.data.dto.CreateBookDto
import com.example.myapplication.data.dto.LoginRequestDto
import com.example.myapplication.data.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("collections/users/auth-with-password")
    suspend fun loginAsync(
        @Body loginRequestDto: LoginRequestDto
    ) : LoginResponseDto

    @GET("collections/books/records")
    suspend fun getAllBook() : ApiCollection<BookDto>

    @GET("collections/books/records/{id}")
    suspend fun getAllBook(
        @Path("id") id: String
    ) : BookDto

    @GET("collections/category/records")
    suspend fun getAllCategory() : ApiCollection<CategoryDto>

    @POST("collections/books/records")
    suspend fun createBook(
        @Body createBookDto: CreateBookDto
    )

    @PATCH("collections/books/records/{id}")
    suspend fun updateBook(
        @Path("id") id: String,
        @Body createBookDto: CreateBookDto
    )

}