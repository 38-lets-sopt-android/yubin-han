package com.example.letssopt.core.data.network.datasource.api

import com.example.letssopt.core.data.dto.GetUserProfileResponse
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun getUserProfile(
        userId: String,
    ): Response<GetUserProfileResponse>
}
