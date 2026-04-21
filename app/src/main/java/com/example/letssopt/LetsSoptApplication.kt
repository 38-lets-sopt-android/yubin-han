package com.example.letssopt


import android.app.Application
import com.example.letssopt.core.data.repository.UserRepository

class LetsSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        UserRepository.init(this)
    }
}
