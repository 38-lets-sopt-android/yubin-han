package com.example.letssopt.presentation.onboarding.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: AuthRepository = AuthRepository
) : ViewModel() {
    var uiState by mutableStateOf(LoginContract.UiState())
        private set
    private val _effect = Channel<LoginContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun updateEmail(text: String) {
        uiState = uiState.copy(emailText = text)
    }

    fun updatePw(text: String) {
        uiState = uiState.copy(pwText = text)
    }

    fun login() {
        viewModelScope.launch {
            val savedEmail = userRepository.getEmail()
            val savedPw = userRepository.getPassword()

            if (uiState.emailText == savedEmail && uiState.pwText == savedPw) {
                userRepository.setLoggedIn(true)
                _effect.send(LoginContract.Effect.ShowToast("로그인에 성공했습니다"))
                _effect.send(LoginContract.Effect.NavigateToMain)
            } else {
                _effect.send(LoginContract.Effect.ShowToast("이메일 또는 비밀번호가 일치하지 않습니다."))
            }
        }
    }
}
