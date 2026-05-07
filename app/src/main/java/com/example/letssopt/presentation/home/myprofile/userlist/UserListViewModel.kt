package com.example.letssopt.presentation.home.myprofile.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.letssopt.data.repository.api.UserRepository
import com.example.letssopt.data.repository.paging.UserPagingSource
import androidx.paging.PagingData
import com.example.letssopt.data.model.profile.UserProfile
import kotlinx.coroutines.flow.Flow

class UserListViewModel(
    private val userRepository: UserRepository = UserRepository.getInstance()
) : ViewModel() {
    val userPagingData: Flow<PagingData<UserProfile>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { UserPagingSource(userRepository) }
    ).flow.cachedIn(viewModelScope)

}
