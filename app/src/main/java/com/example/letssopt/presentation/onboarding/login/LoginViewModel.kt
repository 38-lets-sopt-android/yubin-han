package com.example.letssopt.presentation.onboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.api.AuthException
import com.example.letssopt.core.data.repository.api.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: AuthRepository = AuthRepository.getInstance()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginContract.UiState())
    val uiState: StateFlow<LoginContract.UiState> = _uiState.asStateFlow()

    private val _effect = Channel<LoginContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun updateId(text: String) {
        _uiState.update { it.copy(idText = text) }
    }

    fun updatePw(text: String) {
        _uiState.update { it.copy(pwText = text) }
    }

    fun login() {
        viewModelScope.launch {
            val currentState = _uiState.value
            userRepository.login(currentState.idText, currentState.pwText)
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
