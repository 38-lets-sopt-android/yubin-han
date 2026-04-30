package com.example.letssopt.core.data.repository.api

import android.content.Context
import com.example.letssopt.core.data.repository.impl.AuthRepositoryImpl

interface AuthRepository {
    suspend fun signUp(email: String, pw: String): Result<Unit>
    suspend fun login(email: String, pw: String): Result<Unit>
    fun getLoggedIn(): Boolean
    fun setLoggedIn(isLoggedIn: Boolean)
    fun getEmail(): String?
    fun getPassword(): String?
    companion object {
        private lateinit var _instance: AuthRepository

        fun init(context: Context) {
            _instance = AuthRepositoryImpl(context)
        }

        fun getInstance(): AuthRepository = _instance
    }
}
sealed class AuthException : Exception() {
    class InvalidCredentials : AuthException()
}
