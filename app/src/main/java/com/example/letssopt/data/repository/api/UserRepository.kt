package com.example.letssopt.data.repository.api

import com.example.letssopt.data.model.profile.UserProfile
import com.example.letssopt.data.remote.datasource.api.UserRemoteDataSource
import com.example.letssopt.data.repository.impl.UserRepositoryImpl

interface UserRepository {
    suspend fun getMyProfile(): Result<UserProfile>
    suspend fun getUserList(page: Int): Result<List<UserProfile>>

    companion object {
        private lateinit var _instance: UserRepository

        fun init(userRemoteDataSource: UserRemoteDataSource) {
            _instance = UserRepositoryImpl(userRemoteDataSource)
        }

        fun getInstance(): UserRepository = _instance
    }
}
