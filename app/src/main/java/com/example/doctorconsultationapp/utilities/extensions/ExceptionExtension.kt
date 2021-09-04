package com.example.doctorconsultationapp.utilities.extensions

import com.example.doctorconsultationapp.data.response.NetworkResponseData
import com.example.doctorconsultationapp.utilities.enums.RequestStatus
import org.json.JSONException
import retrofit2.HttpException
import java.text.ParseException

fun Exception.handleExceptionForRequests(): NetworkResponseData.Exception{
    return when(this){
        is HttpException -> {
            NetworkResponseData.Exception(RequestStatus.EXCEPTION,this.code(),this.message)
        }
        is JSONException -> {
            NetworkResponseData.Exception(RequestStatus.EXCEPTION,0,this.toString())
        }
        else -> {
            NetworkResponseData.Exception(RequestStatus.EXCEPTION,0,this.toString())
        }
    }
}