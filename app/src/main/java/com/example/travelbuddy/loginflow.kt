package com.example.travelbuddy

import android.app.Application
import com.google.firebase.FirebaseApp

class loginflow:Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

    }
}