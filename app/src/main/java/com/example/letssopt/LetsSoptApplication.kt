package com.example.letssopt
import android.app.Application
import com.example.letssopt.core.network.RetrofitClient
import com.example.letssopt.data.remote.datasource.impl.AuthRemoteDataSourceImpl
import com.example.letssopt.data.remote.datasource.impl.UserRemoteDataSourceImpl

class LetsSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        _root_ide_package_.com.example.letssopt.data.repository.api.AuthRepository.init(
            this,
            AuthRemoteDataSourceImpl(
                RetrofitClient.authService
            )
        )
        _root_ide_package_.com.example.letssopt.data.repository.api.UserRepository.init(
            UserRemoteDataSourceImpl(
                RetrofitClient.userService
            )
        )
        _root_ide_package_.com.example.letssopt.data.repository.api.StorageRepository.init(
            _root_ide_package_.com.example.letssopt.data.local.AppDatabase.getDatabase(this).storedItemDao()
        )
    }
}

