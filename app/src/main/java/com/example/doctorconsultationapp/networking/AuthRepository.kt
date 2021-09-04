package com.example.doctorconsultationapp.networking

import com.example.doctorconsultationapp.data.requests.Auth
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun login(loginCredentials: Auth) = apiService.login(loginCredentials)
}