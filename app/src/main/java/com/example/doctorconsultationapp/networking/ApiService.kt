package com.example.doctorconsultationapp.networking

import com.example.doctorconsultationapp.data.DummyResponse
import com.example.doctorconsultationapp.data.LoginData
import com.example.doctorconsultationapp.data.LoginResponse
import com.example.doctorconsultationapp.data.requests.Auth
import com.example.doctorconsultationapp.utilities.constants.AuthEndpoints
import com.example.doctorconsultationapp.utilities.constants.MiscEndpoints
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    //region Authentication
    @POST(AuthEndpoints.doctorLogin)
     suspend fun login(@Body loginCredentials: Auth): LoginResponse


    //endregion


}