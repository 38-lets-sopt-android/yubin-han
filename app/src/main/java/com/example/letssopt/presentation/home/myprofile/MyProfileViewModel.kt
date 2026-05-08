package com.example.letssopt.presentation.home.myprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.repository.api.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyProfileViewModel(
    private val userRepository: UserRepository = UserRepository.getInstance()
) : ViewModel() {
    private val _uiState = MutableStateFlow(MyProfileContract.UiState())
    val uiState: StateFlow<MyProfileContract.UiState> = _uiState.asStateFlow()

    init {
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            userRepository.getMyProfile()
                .onSuccess { profile ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            myProfile = profile,
                        )
                    }
                }
                .onFailure {
                    _uiState.update { it.copy(isLoading = false) }
                }
        }
    }
}
