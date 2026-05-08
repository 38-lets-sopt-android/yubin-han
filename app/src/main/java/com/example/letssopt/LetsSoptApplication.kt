package com.example.letssopt
import android.app.Application
import com.example.letssopt.core.network.RetrofitClient
import com.example.letssopt.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.example.letssopt.data.remote.datasource.impl.UserRemoteDataSourceImpl

import com.example.letssopt.data.repository.api.AuthRepository
import com.example.letssopt.data.repository.api.UserRepository
import com.example.letssopt.data.repository.api.StorageRepository
import com.example.letssopt.data.local.AppDatabase

class LetsSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AuthRepository.init(
            this,
            AuthRemoteDataSourceImpl(
                RetrofitClient.authService
            )
        )
        UserRepository.init(
            UserRemoteDataSourceImpl(
                RetrofitClient.userService
            )
        )
        StorageRepository.init(
            AppDatabase.getDatabase(this).storedItemDao()
        )
    }
}

