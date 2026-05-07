package com.example.letssopt.data.repository.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.letssopt.core.data.dto.PostSignInRequest
import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.network.datasource.api.AuthRemoteDataSource
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.core.util.parseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class AuthRepositoryImpl(
    private val context: Context,
    private val authDataSource: AuthRemoteDataSource
) : AuthRepository {

    private object PreferencesKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
        val ID = stringPreferencesKey("ID")
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
                }
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.parseError()))
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
                }
                Result.success(Unit)
            } else {
                Result.failure(Exception(response.parseError()))
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
}
