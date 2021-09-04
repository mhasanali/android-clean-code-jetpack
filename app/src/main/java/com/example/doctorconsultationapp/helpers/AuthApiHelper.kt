package com.example.doctorconsultationapp.helpers


import com.example.doctorconsultationapp.data.LoginResponse
import com.example.doctorconsultationapp.data.requests.Auth
import com.example.doctorconsultationapp.data.response.NetworkResponseData
import com.example.doctorconsultationapp.networking.AuthRepository
import com.example.doctorconsultationapp.utilities.constants.NetworkConstants
import kotlinx.coroutines.*

import javax.inject.Inject

class AuthApiHelper @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun login(loginCredentials: Auth): NetworkResponseData<LoginResponse> {
        return NetworkConstants.performRequest { runBlocking { authRepository.login(loginCredentials) } }
    }

}