package com.example.doctorconsultationapp.utilities.constants

import com.example.doctorconsultationapp.data.response.NetworkResponseData
import com.example.doctorconsultationapp.utilities.enums.RequestStatus
import com.example.doctorconsultationapp.utilities.extensions.handleExceptionForRequests
import okhttp3.Credentials
import java.lang.Exception

object NetworkConstants{

                const val authUsername = ""
                const val authPassword = ""
                const val consultationURL = ""
                const val consultationURLV2 = ""
                const val baseURL = ""
                const val dummyURL = "https://webhook.site/"
                const val requestTimeoutInSeconds: Long = 120
                val basicAuth = Credentials.basic(authUsername, authPassword)

                   fun <T> performRequest(apiMethodToCall: () -> T): NetworkResponseData<T> {
                        var result: NetworkResponseData<T>
                        try {
                                val res = apiMethodToCall()
                                if(res == null){
                                     result = NetworkResponseData.Failure(RequestStatus.ERROR,"error")
                                }else{
                                    result = NetworkResponseData.Success(RequestStatus.SUCCESS,res)
                                }
                        }
                        catch (ex: Exception) {
                                result = ex.handleExceptionForRequests()
                        }
                        return result
                }

}
