package com.example.doctorconsultationapp.viewmodel.auth

import android.view.View
import androidx.lifecycle.*
import com.example.doctorconsultationapp.data.DummyResponse
import com.example.doctorconsultationapp.data.LoginData
import com.example.doctorconsultationapp.data.LoginResponse
import com.example.doctorconsultationapp.data.requests.Auth

import com.example.doctorconsultationapp.data.response.NetworkResponseData
import com.example.doctorconsultationapp.helpers.AuthApiHelper
import com.example.doctorconsultationapp.utilities.db.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApiHelper: AuthApiHelper,
    private val userPreferences: UserPreferences
) : ViewModel(){
//init {
//    viewModelScope.launch {
//        userPreferences.deleteUserPrefs()
//
//    }
//}

    var mEmail = MutableLiveData<String>("")
    var mPassword = MutableLiveData<String>("")
    var isLoading = MutableLiveData(false)
    private var mutableLoginData = MutableLiveData<NetworkResponseData<LoginResponse>>()


    fun getLogin():LiveData<NetworkResponseData<LoginResponse>>{
        return mutableLoginData
    }

     fun login(email: String, password: String){
         val loginCredentials = Auth(email,password)
         mutableLoginData.value = NetworkResponseData.Loading()
         GlobalScope.async (Dispatchers.Main) {
            mutableLoginData.value = authApiHelper.login(loginCredentials)
        }
    }

//    fun saveData(){
//        viewModelScope.launch {
//            userPreferences.saveUserName("Ali")
//        }
//    }

}