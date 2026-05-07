package com.example.letssopt.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.letssopt.data.model.profile.UserProfile
import com.example.letssopt.data.repository.api.UserRepository

class UserPagingSource(
    private val userRepository: UserRepository
) : PagingSource<Int, UserProfile>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserProfile> {
        return try {
            val response = userRepository.getUserList(1).getOrThrow()
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserProfile>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
