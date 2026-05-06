package com.example.letssopt.core.data.network.service

import com.example.letssopt.core.data.dto.GetUserProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/v1/users/{userId}")
    suspend fun getUserProfile(
        @Path("userId")
        userId: String,
    ): Response<GetUserProfileResponse>
}
