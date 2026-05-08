package com.example.letssopt.data.remote.datasource.api

import com.example.letssopt.data.remote.dto.PostSignInRequest
import com.example.letssopt.data.remote.dto.PostSignInResponse
import com.example.letssopt.data.remote.dto.PostSignUpRequest
import com.example.letssopt.data.remote.dto.PostSignUpResponse
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun signUp(request: PostSignUpRequest): Response<PostSignUpResponse>

    suspend fun signIn(request: PostSignInRequest): Response<PostSignInResponse>
}
