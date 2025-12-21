package com.example.myapplication.data.repository

import com.example.myapplication.data.mapper.LoginMapper
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.model.LoginResponse
import com.example.myapplication.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AuthRepository {


    override suspend fun loginAsync(loginRequest: LoginRequest): LoginResponse {
        return LoginMapper
            .toResponseDomain(apiService.loginAsync(LoginMapper.toRequestDto(loginRequest)))
    }


}