package com.example.letssopt.core.data.repository.api

import android.content.Context
import com.example.letssopt.core.data.network.datasource.api.AuthDataSource
import com.example.letssopt.core.data.repository.impl.AuthRepositoryImpl

interface AuthRepository {
    suspend fun signUp(
        id: String,
        pw: String,
        name: String,
        email: String,
        age: Int,
        part: String
    ): Result<Unit>
    suspend fun login(id: String, pw: String): Result<Unit>
    fun getLoggedIn(): Boolean
    fun setLoggedIn(isLoggedIn: Boolean)
    fun getId(): String?
    fun getEmail(): String?
    fun getPassword(): String?
    fun getName(): String?
    fun getAge(): Int?
    fun getPart(): String?
    fun setId(id: String)
    fun setEmail(email: String)
    fun setPassword(password: String)
    fun setName(name: String)
    fun setAge(age: Int)
    fun setPart(part: String)
    companion object {
        private lateinit var _instance: AuthRepository

        fun init(context: Context, authDataSource: AuthDataSource) {
            _instance = AuthRepositoryImpl(context, authDataSource)
        }

        fun getInstance(): AuthRepository = _instance
    }
}
sealed class AuthException : Exception() {
    class InvalidCredentials : AuthException()
}
