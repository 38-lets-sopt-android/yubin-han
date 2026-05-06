package com.example.letssopt.core.data.repository.impl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.dto.PostSignUpResponse
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.datasource.api.AuthDataSource
import com.example.letssopt.core.data.repository.api.AuthException
import com.example.letssopt.core.data.repository.api.AuthRepository

class AuthRepositoryImpl(
    context: Context,
    private val authDataSource: AuthDataSource
) : AuthRepository {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("USER_PREFS", Context.MODE_PRIVATE)

    override suspend fun signUp(
        id: String,
        pw: String,
        name: String,
        email: String,
        age: Int,
        part: String
    ): Result<Unit> {
        return try {
            val response = authDataSource.signUp(
                PostSignUpRequest(
                    loginId = id,
                    password = pw,
                    name = name,
                    email = email,
                    age = age,
                    part = part
                )
            )
            if (response.isSuccessful) {
                prefs.edit {
                    putString("ID", id)
                    putString("PW", pw)
                    putString("NAME", name)
                    putString("EMAIL", email)
                    putInt("AGE", age)
                    putString("PART", part)
                }
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        RetrofitClient.json.decodeFromString<PostSignUpResponse>(errorBody).message
                    } catch (e: Exception) {
                        response.message()
                    }
                } else {
                    response.message()
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(id: String, pw: String): Result<Unit> {
        val savedId = prefs.getString("ID", null)
        val savedPw = prefs.getString("PW", null)

        return if (id == savedId && pw == savedPw) {
            setLoggedIn(true)
            Result.success(Unit)
        } else {
            Result.failure(AuthException.InvalidCredentials())
        }
    }

    override fun getLoggedIn(): Boolean = prefs.getBoolean("IS_LOGGED_IN", false)

    override fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit { putBoolean("IS_LOGGED_IN", isLoggedIn) }
    }

    override fun getId(): String? = prefs.getString("ID", null)
    override fun getEmail(): String? = prefs.getString("EMAIL", null)
    override fun getPassword(): String? = prefs.getString("PW", null)
    override fun getName(): String? = prefs.getString("NAME", null)
    override fun getAge(): Int? {
        val age = prefs.getInt("AGE", -1)
        return if (age == -1) null else age
    }

    override fun getPart(): String? = prefs.getString("PART", null)

    override fun setId(id: String) = prefs.edit { putString("ID", id) }
    override fun setEmail(email: String) = prefs.edit { putString("EMAIL", email) }
    override fun setPassword(password: String) = prefs.edit { putString("PW", password) }
    override fun setName(name: String) = prefs.edit { putString("NAME", name) }
    override fun setAge(age: Int) = prefs.edit { putInt("AGE", age) }
    override fun setPart(part: String) = prefs.edit { putString("PART", part) }
}
