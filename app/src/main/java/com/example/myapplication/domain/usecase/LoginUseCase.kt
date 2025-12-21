package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.AppSession
import com.example.myapplication.domain.model.CustomResult
import com.example.myapplication.domain.model.LoginRequest
import com.example.myapplication.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val appSession: AppSession
) {

    suspend operator fun invoke(loginRequest: LoginRequest) : CustomResult<Unit>{

        try {

            val response = authRepository.loginAsync(loginRequest)

            appSession.setSession(response)

            return CustomResult.Success(Unit)
        }
        catch (ex: Exception){

            return CustomResult.Failure(ex.message ?: "")

        }

    }

}