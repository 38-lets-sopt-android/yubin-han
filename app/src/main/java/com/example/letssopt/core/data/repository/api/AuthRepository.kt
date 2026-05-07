package com.example.letssopt.core.data.repository.api

import android.content.Context
import com.example.letssopt.core.data.network.datasource.api.AuthRemoteDataSource
import com.example.letssopt.core.data.repository.impl.AuthRepositoryImpl
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(
        id: String,
        pw: String,
        name: String,
        email: String,
        age: Int,
        part: String
    ): Result<Unit>

    suspend fun signIn(id: String, pw: String): Result<Unit>

    fun getLoggedIn(): Flow<Boolean>
    suspend fun setLoggedIn(isLoggedIn: Boolean)

    fun getId(): Flow<String?>

    companion object {
        private lateinit var _instance: AuthRepository

        fun init(context: Context, authDataSource: AuthRemoteDataSource) {
            _instance = AuthRepositoryImpl(context, authDataSource)
        }

        fun getInstance(): AuthRepository = _instance
    }
}
