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
    fun getEmail(): Flow<String?>
    fun getPassword(): Flow<String?>
    fun getName(): Flow<String?>
    fun getAge(): Flow<Int?>
    fun getPart(): Flow<String?>

    suspend fun setId(id: String)
    suspend fun setEmail(email: String)
    suspend fun setPassword(password: String)
    suspend fun setName(name: String)
    suspend fun setAge(age: Int)
    suspend fun setPart(part: String)

    companion object {
        private lateinit var _instance: AuthRepository

        fun init(context: Context, authDataSource: AuthRemoteDataSource) {
            _instance = AuthRepositoryImpl(context, authDataSource)
        }

        fun getInstance(): AuthRepository = _instance
    }
}
