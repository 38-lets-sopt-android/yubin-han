package com.example.letssopt.data.repository.impl

import com.example.letssopt.core.util.parseError
import com.example.letssopt.core.util.safeRunCatching
import com.example.letssopt.data.model.profile.UserProfile
import com.example.letssopt.data.remote.datasource.api.UserRemoteDataSource
import com.example.letssopt.data.repository.api.AuthRepository
import com.example.letssopt.data.repository.api.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getMyProfile(): Result<UserProfile> = safeRunCatching {
        val userId = AuthRepository.getInstance().getId().firstOrNull()
            ?: throw Exception("유저를 찾을 수 없습니다")

        val response = userRemoteDataSource.getUserProfile(userId)

        if (response.isSuccessful) {
            val profileData = response.body()?.data
                ?: throw Exception("데이터를 불러올 수 없습니다")
            UserProfile(
                id = profileData.logInId,
                name = profileData.name,
                email = profileData.email,
                age = profileData.age,
                part = profileData.part
            )
        } else {
            throw Exception(response.parseError())
        }
    }

    override suspend fun getUserList(page: Int): Result<List<UserProfile>> = safeRunCatching {
        val response = userRemoteDataSource.getRecentUsers()
        if (response.isSuccessful) {
            response.body()?.data?.users?.map { data ->
                UserProfile(
                    id = data.id.toString(),
                    name = data.name,
                    part = data.part,
                )
            } ?: emptyList()
        } else {
            throw Exception(response.parseError())
        }
    }
}
