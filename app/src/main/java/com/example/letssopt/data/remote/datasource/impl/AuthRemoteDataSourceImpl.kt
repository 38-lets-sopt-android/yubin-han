package com.example.letssopt.data.remote.datasource.impl

import com.example.letssopt.data.remote.dto.PostSignInRequest
import com.example.letssopt.data.remote.dto.PostSignInResponse
import com.example.letssopt.data.remote.dto.PostSignUpRequest
import com.example.letssopt.data.remote.dto.PostSignUpResponse
import com.example.letssopt.data.remote.datasource.api.AuthRemoteDataSource
import com.example.letssopt.data.remote.service.AuthService
import retrofit2.Response

class AuthRemoteDataSourceImpl(
    private val authService: AuthService
) : AuthRemoteDataSource {
    override suspend fun signUp(request: PostSignUpRequest): Response<PostSignUpResponse> {
        return authService.signUp(request)
    }

    override suspend fun signIn(request: PostSignInRequest): Response<PostSignInResponse> {
        return authService.signIn(request)
    }
}
