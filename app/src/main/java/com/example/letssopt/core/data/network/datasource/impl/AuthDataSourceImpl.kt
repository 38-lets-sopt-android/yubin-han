package com.example.letssopt.core.data.network.datasource.impl

import com.example.letssopt.core.data.dto.PostSignInRequest
import com.example.letssopt.core.data.dto.PostSignInResponse
import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.dto.PostSignUpResponse
import com.example.letssopt.core.data.network.datasource.api.AuthDataSource
import com.example.letssopt.core.data.network.service.AuthService
import retrofit2.Response

class AuthDataSourceImpl(
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun signUp(request: PostSignUpRequest): Response<PostSignUpResponse> {
        return authService.signUp(request)
    }

    override suspend fun signIn(request: PostSignInRequest): Response<PostSignInResponse> {
        return authService.signIn(request)
    }
}
