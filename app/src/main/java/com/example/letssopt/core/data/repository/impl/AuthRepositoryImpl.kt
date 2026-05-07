package com.example.letssopt.core.data.repository.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.letssopt.core.data.dto.PostSignInRequest
import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.datasource.api.AuthRemoteDataSource
import com.example.letssopt.core.data.repository.api.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class AuthRepositoryImpl(
    private val context: Context,
    private val authDataSource: AuthRemoteDataSource
) : AuthRepository {

    private object PreferencesKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
        val ID = stringPreferencesKey("ID")
        val PW = stringPreferencesKey("PW")
        val NAME = stringPreferencesKey("NAME")
        val EMAIL = stringPreferencesKey("EMAIL")
        val AGE = intPreferencesKey("AGE")
        val PART = stringPreferencesKey("PART")
    }

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
                context.dataStore.edit { prefs ->
                    prefs[PreferencesKeys.ID] = id
                    prefs[PreferencesKeys.PW] = pw
                    prefs[PreferencesKeys.NAME] = name
                    prefs[PreferencesKeys.EMAIL] = email
                    prefs[PreferencesKeys.AGE] = age
                    prefs[PreferencesKeys.PART] = part
                }
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (!errorBody.isNullOrBlank()) {
                    try {
                        val jsonElement = RetrofitClient.json.parseToJsonElement(errorBody)
                        jsonElement.jsonObject["message"]?.jsonPrimitive?.content 
                            ?: "회원가입 실패 (${response.code()})"
                    } catch (e: Exception) {
                        "오류 발생: ${response.message()}"
                    }
                } else {
                    "서버 응답 없음 (${response.code()})"
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signIn(id: String, pw: String): Result<Unit> {
        return try {
            val response = authDataSource.signIn(
                PostSignInRequest(
                    loginId = id,
                    password = pw
                )
            )
            if (response.isSuccessful) {
                setLoggedIn(true)
                val userData = response.body()?.data

                context.dataStore.edit { prefs ->
                    prefs[PreferencesKeys.ID] = userData?.userId?.toString() ?: id
                    prefs[PreferencesKeys.PW] = pw
                }
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (!errorBody.isNullOrBlank()) {
                    try {
                        val jsonElement = RetrofitClient.json.parseToJsonElement(errorBody)
                        jsonElement.jsonObject["message"]?.jsonPrimitive?.content 
                            ?: "로그인 실패 (${response.code()})"
                    } catch (e: Exception) {
                        "오류 발생: ${response.message()}"
                    }
                } else {
                    "서버 응답 없음 (${response.code()})"
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getLoggedIn(): Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { it[PreferencesKeys.IS_LOGGED_IN] ?: false }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { it[PreferencesKeys.IS_LOGGED_IN] = isLoggedIn }
    }

    override fun getId(): Flow<String?> = context.dataStore.data.map { it[PreferencesKeys.ID] }
    override fun getEmail(): Flow<String?> =
        context.dataStore.data.map { it[PreferencesKeys.EMAIL] }

    override fun getPassword(): Flow<String?> =
        context.dataStore.data.map { it[PreferencesKeys.PW] }

    override fun getName(): Flow<String?> = context.dataStore.data.map { it[PreferencesKeys.NAME] }
    override fun getAge(): Flow<Int?> = context.dataStore.data.map {
        val age = it[PreferencesKeys.AGE] ?: -1
        if (age == -1) null else age
    }

    override fun getPart(): Flow<String?> = context.dataStore.data.map { it[PreferencesKeys.PART] }

    override suspend fun setId(id: String) {
        context.dataStore.edit { it[PreferencesKeys.ID] = id }
    }

    override suspend fun setEmail(email: String) {
        context.dataStore.edit { it[PreferencesKeys.EMAIL] = email }
    }

    override suspend fun setPassword(password: String) {
        context.dataStore.edit { it[PreferencesKeys.PW] = password }
    }

    override suspend fun setName(name: String) {
        context.dataStore.edit { it[PreferencesKeys.NAME] = name }
    }

    override suspend fun setAge(age: Int) {
        context.dataStore.edit { it[PreferencesKeys.AGE] = age }
    }

    override suspend fun setPart(part: String) {
        context.dataStore.edit { it[PreferencesKeys.PART] = part }
    }
}
