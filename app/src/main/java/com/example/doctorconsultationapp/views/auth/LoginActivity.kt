package com.example.doctorconsultationapp.views.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import com.example.doctorconsultationapp.R
import com.example.doctorconsultationapp.data.response.NetworkResponseData
import com.example.doctorconsultationapp.databinding.ActivityLoginBinding
import com.example.doctorconsultationapp.utilities.db.UserPreferences
import com.example.doctorconsultationapp.utilities.extensions.showToast
import com.example.doctorconsultationapp.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity(){
    @Inject
    lateinit var userPreferences: UserPreferences

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var loginActivityBinding: ActivityLoginBinding
    var name: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginActivityBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginActivityBinding.lifecycleOwner = this
        loginActivityBinding.loginViewModel = loginViewModel
        observeLoginStatus()
//        userPreferences.userName.asLiveData().observe(this){
//            name = it
//        }
//        print(name)
    }


    fun observeLoginStatus(){

        loginViewModel.getLogin().observe(this, {
            when(it){
                is NetworkResponseData.Loading -> {
                    loginViewModel.isLoading.value = true
                }
                is NetworkResponseData.Success -> {
                    loginViewModel.isLoading.value = false
                    if(it.data?.status == 200){
                        showToast(name)
                        //todo Implementation after logging in
//                        loginViewModel.saveData()
                    }
                }
                else -> {
                    loginViewModel.isLoading.value = false
                    //todo implementation for failure login
                    //all other exceptions
                }
            }
        })
    }

}