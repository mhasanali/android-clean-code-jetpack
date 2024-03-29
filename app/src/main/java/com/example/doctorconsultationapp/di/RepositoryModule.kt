package com.example.doctorconsultationapp.di

import com.example.doctorconsultationapp.networking.ApiService
import com.example.doctorconsultationapp.networking.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideAuthRepository(apiService: ApiService): AuthRepository = AuthRepository(apiService)
}