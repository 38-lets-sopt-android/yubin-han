package com.example.letssopt.data.remote.datasource.impl


import com.example.letssopt.data.remote.dto.GetRecentUsersResponse
import com.example.letssopt.data.remote.dto.GetUserProfileResponse
import com.example.letssopt.data.remote.datasource.api.UserRemoteDataSource
import com.example.letssopt.data.remote.service.UserService
import retrofit2.Response


class UserRemoteDataSourceImpl(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun getUserProfile(userId: String): Response<GetUserProfileResponse> {
        return userService.getUserProfile(userId = userId)
    }

    override suspend fun getRecentUsers(): Response<GetRecentUsersResponse> {
        return userService.getRecentUsers()
    }
}
