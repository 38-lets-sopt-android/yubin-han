package com.example.letssopt.data.network.service

import com.example.letssopt.data.dto.GetRecentUsersResponse
import com.example.letssopt.data.dto.GetUserProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/v1/users/{userId}")
    suspend fun getUserProfile(
        @Path("userId")
        userId: String,
    ): Response<GetUserProfileResponse>

    @GET("api/v1/users")
    suspend fun getRecentUsers(
    ): Response<GetRecentUsersResponse>

}
