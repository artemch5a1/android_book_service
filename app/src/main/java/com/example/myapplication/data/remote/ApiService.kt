package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.LoginRequestDto
import com.example.myapplication.data.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("collections/users/auth-with-password")
    suspend fun loginAsync(
        @Body loginRequestDto: LoginRequestDto
    ) : LoginResponseDto

}