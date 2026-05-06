package com.example.letssopt.core.data.repository.impl

import com.example.letssopt.core.data.dto.GetUserProfileResponse
import com.example.letssopt.core.data.model.profile.UserProfile
import com.example.letssopt.core.data.network.RetrofitClient
import com.example.letssopt.core.data.network.datasource.api.UserRemoteDataSource
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.core.data.repository.api.UserRepository
import kotlinx.coroutines.flow.firstOrNull

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getMyProfile(): Result<UserProfile> {
        return try {
            val userId = AuthRepository.getInstance().getId().firstOrNull() ?: return Result.failure(Exception("User ID not found"))
            val response = userRemoteDataSource.getUserProfile(userId)
            
            if (response.isSuccessful) {
                val profileData = response.body()?.data ?: return Result.failure(Exception("Response body is null"))
                val user = UserProfile(
                    id = profileData.logInId,
                    name = profileData.name,
                    email = profileData.email,
                    age = profileData.age,
                    part = profileData.part
                )
                Result.success(user)
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = if (errorBody != null) {
                    try {
                        RetrofitClient.json.decodeFromString<GetUserProfileResponse>(errorBody).message
                    } catch (e: Exception) {
                        response.message()
                    }
                } else {
                    response.message()
                }
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserList(page: Int): Result<List<UserProfile>> {
        return try {
            // TODO: API 연동
            val mockList = listOf(
                UserProfile(
                    id = "id_$page",
                    name = "User $page-1",
                    email = "user$page@example.com",
                    age = 20 + page,
                    part = "Android"
                ),
                UserProfile(
                    id = "id_${page}_2",
                    name = "User $page-2",
                    email = "user${page}_2@example.com",
                    age = 21 + page,
                    part = "iOS"
                )
            )
            Result.success(mockList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
