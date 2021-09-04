package com.example.doctorconsultationapp.helpers

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DoctorApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}