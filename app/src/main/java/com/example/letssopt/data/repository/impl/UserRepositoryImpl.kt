package com.example.letssopt.data.repository.impl

import com.example.letssopt.core.data.model.profile.UserProfile
import com.example.letssopt.core.data.network.datasource.api.UserRemoteDataSource
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.core.data.repository.api.UserRepository
import com.example.letssopt.core.util.parseError
import kotlinx.coroutines.flow.firstOrNull

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
) : UserRepository {

    override suspend fun getMyProfile(): Result<UserProfile> {
        return try {
            val userId =
                AuthRepository.getInstance().getId().firstOrNull() ?: return Result.failure(
                    Exception("유저를 찾을 수 없습니다")
                )
            val response = userRemoteDataSource.getUserProfile(userId)

            if (response.isSuccessful) {
                val profileData = response.body()?.data
                    ?: return Result.failure(Exception("데이터를 불러올 수 없습니다"))
                val user = UserProfile(
                    id = profileData.logInId,
                    name = profileData.name,
                    email = profileData.email,
                    age = profileData.age,
                    part = profileData.part
                )
                Result.success(user)
            } else {
                Result.failure(Exception(response.parseError()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserList(page: Int): Result<List<UserProfile>> {
        return try {
            val response = userRemoteDataSource.getRecentUsers()
            if (response.isSuccessful) {
                val users = response.body()?.data?.users?.map { data ->
                    UserProfile(
                        id = data.id.toString(),
                        name = data.name,
                        part = data.part,
                    )
                } ?: emptyList()
                Result.success(users)
            } else {
                Result.failure(Exception(response.parseError()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
