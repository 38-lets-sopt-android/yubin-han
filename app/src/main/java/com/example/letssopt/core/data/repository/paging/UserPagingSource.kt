package com.example.letssopt.core.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.letssopt.core.data.model.profile.UserProfile
import com.example.letssopt.core.data.repository.api.UserRepository

class UserPagingSource(
    private val userRepository: UserRepository
) : PagingSource<Int, UserProfile>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserProfile> {
        val page = params.key ?: 1
        return try {
            val response = userRepository.getUserList(page).getOrThrow()
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
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
