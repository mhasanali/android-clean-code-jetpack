package com.example.doctorconsultationapp.di

import com.example.doctorconsultationapp.helpers.AuthApiHelper
import com.example.doctorconsultationapp.networking.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
class ApiHelperModule {
    @Provides
    fun provideAuthApiHelper(authRepository: AuthRepository): AuthApiHelper = AuthApiHelper(authRepository)
}