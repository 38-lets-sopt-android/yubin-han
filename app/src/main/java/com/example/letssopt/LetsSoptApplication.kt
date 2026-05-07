package com.example.letssopt
import android.app.Application
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.datasource.impl.AuthRemoteDataSourceImpl
import com.example.letssopt.core.data.network.datasource.impl.UserRemoteDataSourceImpl
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.core.data.repository.api.UserRepository

class LetsSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AuthRepository.init(
            this,
            AuthRemoteDataSourceImpl(RetrofitClient.authService)
        )
        UserRepository.init(
            UserRemoteDataSourceImpl(RetrofitClient.userService)
        )
    }
}

