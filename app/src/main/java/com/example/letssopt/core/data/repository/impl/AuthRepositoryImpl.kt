package com.example.letssopt.core.data.repository.impl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.letssopt.core.data.repository.api.AuthException
import com.example.letssopt.core.data.repository.api.AuthRepository

class AuthRepositoryImpl(context: Context) : AuthRepository {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)

    override suspend fun signUp(email: String, pw: String): Result<Unit> {
        return try {
            prefs.edit {
                putString("EMAIL", email)
                putString("PW", pw)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, pw: String): Result<Unit> {
        val savedEmail = prefs.getString("EMAIL", null)
        val savedPw = prefs.getString("PW", null)

        return if (email == savedEmail && pw == savedPw) {
            setLoggedIn(true)
            Result.success(Unit)
        } else {
            Result.failure(AuthException.InvalidCredentials())
        }
    }

    override fun getEmail(): String? = prefs.getString("EMAIL", null)
    override fun getPassword(): String? = prefs.getString("PW", null)
    override fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit { putBoolean("IS_LOGGED_IN", isLoggedIn) }
    }

    override fun getLoggedIn(): Boolean = prefs.getBoolean("IS_LOGGED_IN", false)
}
