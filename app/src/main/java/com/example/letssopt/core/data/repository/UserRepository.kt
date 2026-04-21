package com.example.letssopt.core.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object UserRepository {
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)

    }

    fun saveUser(email: String, pw: String) {
        prefs.edit {
            putString("EMAIL", email)
            putString("PW", pw)
        }
    }

    fun getEmail(): String? = prefs.getString("EMAIL", null)
    fun getPassword(): String? = prefs.getString("PW", null)
    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit {
            putBoolean("IS_LOGGED_IN", isLoggedIn)
        }
    }

    fun getLoggedIn(): Boolean = prefs.getBoolean("IS_LOGGED_IN", false)
}
