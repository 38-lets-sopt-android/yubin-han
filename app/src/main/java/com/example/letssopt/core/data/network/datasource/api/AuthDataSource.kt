package com.example.letssopt.core.data.network.datasource.api

import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.dto.PostSignUpResponse
import retrofit2.Response

interface AuthDataSource {
    suspend fun signUp(request: PostSignUpRequest): Response<PostSignUpResponse>
}
