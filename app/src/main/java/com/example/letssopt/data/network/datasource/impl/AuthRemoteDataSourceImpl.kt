package com.example.letssopt.data.network.datasource.impl

import com.example.letssopt.core.data.dto.PostSignInRequest
import com.example.letssopt.core.data.dto.PostSignInResponse
import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.dto.PostSignUpResponse
import com.example.letssopt.core.data.network.datasource.api.AuthRemoteDataSource
import com.example.letssopt.core.data.network.service.AuthService
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
