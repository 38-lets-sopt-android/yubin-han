package com.example.letssopt.presentation.home.myprofile.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.api.UserRepository
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val userRepository: UserRepository = UserRepository.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserListContract.UiState())
    val uiState: StateFlow<UserListContract.UiState> = _uiState.asStateFlow()

    private val _effect = Channel<UserListContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        fetchUserList()
    }

    private fun fetchUserList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.getUserList()
                .onSuccess { userList ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            users = userList.toPersistentList()
                        )
                    }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                    _effect.send(UserListContract.Effect.ShowToast("유저 리스트를 불러오지 못했습니다."))
                }
        }
    }
}
