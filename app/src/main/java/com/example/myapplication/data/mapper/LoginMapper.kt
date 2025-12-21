package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.LoginDetailDto
import com.example.myapplication.data.dto.LoginRequestDto
import com.example.myapplication.data.dto.LoginResponseDto
import com.example.myapplication.domain.model.LoginDetail
import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.model.LoginResponse

object LoginMapper {

    fun toRequestDto(loginRequest: LoginRequest) : LoginRequestDto {

        return LoginRequestDto(
            identity = loginRequest.email,
            password = loginRequest.password
        )

    }

    fun toResponseDomain(loginResponseDto: LoginResponseDto) : LoginResponse{

        return LoginResponse(
            token = loginResponseDto.token,
            loginDetail = toLoginDetail(loginResponseDto.record)
        )

    }

    fun toLoginDetail(loginDetailDto: LoginDetailDto) : LoginDetail{

        return LoginDetail(
            id = loginDetailDto.id
        )

    }

}