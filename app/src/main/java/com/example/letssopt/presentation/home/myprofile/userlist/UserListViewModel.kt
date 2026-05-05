package com.example.letssopt.presentation.home.myprofile.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.letssopt.core.data.model.home.myprofile.UserProfile
import com.example.letssopt.core.data.repository.api.UserRepository
import com.example.letssopt.core.data.repository.paging.UserPagingSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class UserListViewModel(
    private val userRepository: UserRepository = UserRepository.getInstance()
) : ViewModel() {

    val userPagingData: Flow<PagingData<UserProfile>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UserPagingSource(userRepository) }
    ).flow.cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow(UserListContract.UiState())
    val uiState: StateFlow<UserListContract.UiState> = _uiState.asStateFlow()

    private val _effect = Channel<UserListContract.Effect>()
    val effect = _effect.receiveAsFlow()
}
