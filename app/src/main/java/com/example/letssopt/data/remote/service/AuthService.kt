package com.example.letssopt.data.remote.service

import com.example.letssopt.data.remote.dto.PostSignInRequest
import com.example.letssopt.data.remote.dto.PostSignInResponse
import com.example.letssopt.data.remote.dto.PostSignUpRequest
import com.example.letssopt.data.remote.dto.PostSignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth/signup")
    suspend fun signUp(@Body request: PostSignUpRequest): Response<PostSignUpResponse>
    @POST("api/v1/auth/signin")
    suspend fun signIn(@Body request: PostSignInRequest): Response<PostSignInResponse>
}
