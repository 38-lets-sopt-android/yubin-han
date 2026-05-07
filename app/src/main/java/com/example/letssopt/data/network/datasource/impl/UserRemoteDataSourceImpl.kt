package com.example.letssopt.data.network.datasource.impl


import com.example.letssopt.data.dto.GetRecentUsersResponse
import com.example.letssopt.data.dto.GetUserProfileResponse
import com.example.letssopt.data.network.datasource.api.UserRemoteDataSource
import com.example.letssopt.data.network.service.UserService
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
