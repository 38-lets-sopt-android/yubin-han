package com.example.letssopt.presentation.home.myprofile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.api.UserRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MyProfileViewModel(
    private val userRepository: UserRepository = UserRepository.getInstance()
) : ViewModel() {
    var uiState by mutableStateOf(MyProfileContract.UiState())
        private set

    private val _effect = Channel<MyProfileContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        fetchUserProfile()
    }

    fun navigateToUserList() {
        viewModelScope.launch {
            _effect.send(MyProfileContract.Effect.NavigateToUserList)
        }
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            userRepository.getMyProfile()
                .onSuccess { profile ->
                    uiState = uiState.copy(
                        isLoading = false,
                        myProfile = profile
                    )
                }
                .onFailure {
                    uiState = uiState.copy(isLoading = false)
                }
        }
    }
}
