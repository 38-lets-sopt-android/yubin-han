package com.example.letssopt.presentation.home.myprofile.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.letssopt.core.data.repository.api.UserRepository
import com.example.letssopt.core.data.repository.paging.UserPagingSource

class UserListViewModel(
    private val userRepository: UserRepository = UserRepository.getInstance()
) : ViewModel() {
    val userPagingData = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { UserPagingSource(userRepository) }
    ).flow.cachedIn(viewModelScope)

}
