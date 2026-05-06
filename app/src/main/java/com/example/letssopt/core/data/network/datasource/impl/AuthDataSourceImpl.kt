package com.example.letssopt.core.data.network.datasource.impl

import com.example.letssopt.core.data.dto.PostSignUpRequest
import com.example.letssopt.core.data.dto.PostSignUpResponse
import com.example.letssopt.core.data.network.datasource.api.AuthDataSource
import com.example.letssopt.core.data.network.service.AuthService
import retrofit2.Response

class AuthRemoteDataSourceImpl(
    private val authService: AuthService
) : AuthDataSource {
    override suspend fun signUp(request: PostSignUpRequest): Response<PostSignUpResponse> {
        return authService.signUp(request)
    }
}
