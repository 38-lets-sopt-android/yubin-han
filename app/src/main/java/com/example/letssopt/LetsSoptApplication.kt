package com.example.letssopt


import android.app.Application
import com.example.letssopt.core.data.repository.api.AuthRepository

class LetsSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AuthRepository.init(this)
    }
}
