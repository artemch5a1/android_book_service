package com.example.myapplication.di

import com.example.myapplication.data.remote.ApiFactory
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.repository.AuthRepositoryImpl
import com.example.myapplication.domain.model.AppSession
import com.example.myapplication.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppSession() : AppSession {

        return AppSession()

    }

    @Provides
    @Singleton
    fun provideAuthRepository(appSession: AppSession) : ApiService {

        return ApiFactory.create(appSession)

    }

    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService) : AuthRepository {

        return AuthRepositoryImpl(apiService)

    }

}