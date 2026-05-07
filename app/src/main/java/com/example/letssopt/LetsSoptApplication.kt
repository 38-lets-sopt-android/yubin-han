package com.example.letssopt
import android.app.Application
import com.example.letssopt.data.local.AppDatabase
import com.example.letssopt.data.network.RetrofitClient
import com.example.letssopt.data.network.datasource.impl.AuthRemoteDataSourceImpl
import com.example.letssopt.data.network.datasource.impl.UserRemoteDataSourceImpl
import com.example.letssopt.data.repository.api.AuthRepository
import com.example.letssopt.data.repository.api.StorageRepository
import com.example.letssopt.data.repository.api.UserRepository

class LetsSoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        _root_ide_package_.com.example.letssopt.data.repository.api.AuthRepository.init(
            this,
            _root_ide_package_.com.example.letssopt.data.network.datasource.impl.AuthRemoteDataSourceImpl(
                _root_ide_package_.com.example.letssopt.data.network.RetrofitClient.authService
            )
        )
        _root_ide_package_.com.example.letssopt.data.repository.api.UserRepository.init(
            _root_ide_package_.com.example.letssopt.data.network.datasource.impl.UserRemoteDataSourceImpl(
                _root_ide_package_.com.example.letssopt.data.network.RetrofitClient.userService
            )
        )
        _root_ide_package_.com.example.letssopt.data.repository.api.StorageRepository.init(
            _root_ide_package_.com.example.letssopt.data.local.AppDatabase.getDatabase(this).storedItemDao()
        )
    }
}

