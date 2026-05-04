package com.example.letssopt.presentation.onboarding.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.api.AuthException
import com.example.letssopt.core.data.repository.api.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: AuthRepository = AuthRepository.getInstance()
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
            userRepository.login(uiState.emailText, uiState.pwText)
                .onSuccess {
                    _effect.send(LoginContract.Effect.ShowToast("로그인에 성공했습니다"))
                    _effect.send(LoginContract.Effect.NavigateToMain)
                }
                .onFailure { error ->
                    val message = when (error) {
                        is AuthException.InvalidCredentials ->
                            "이메일 또는 비밀번호가 일치하지 않습니다."

                        else -> "로그인 중 오류가 발생했습니다."
                    }
                    _effect.send(LoginContract.Effect.ShowToast(message))
                }
        }
    }
}
