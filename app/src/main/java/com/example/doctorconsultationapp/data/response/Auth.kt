package com.example.doctorconsultationapp.data

import com.google.gson.annotations.SerializedName

//region Login Model
data class LoginResponse(
    val data: LoginData,
    val status: Int,
    @SerializedName("error_msg")
    val errorMsg: String?
)

data class LoginData(
    val avatar: String,
    val contact: String,
    val description: String,
    val email: String,
    val name: String,
    val qualifications: String,
    val rating: String,
    val service_type: String,
    val status: String,
    val user_id: String
)
//endregion


data class DummyResponse(val message: String?)