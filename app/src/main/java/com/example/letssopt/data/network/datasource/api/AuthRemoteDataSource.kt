package com.example.letssopt.data.network.datasource.api

import com.example.letssopt.core.data.dto.PostSignInRequest
import com.example.letssopt.core.data.dto.PostSignInResponse
import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.dto.PostSignUpResponse
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun signUp(request: PostSignUpRequest): Response<PostSignUpResponse>

    suspend fun signIn(request: PostSignInRequest): Response<PostSignInResponse>
}
