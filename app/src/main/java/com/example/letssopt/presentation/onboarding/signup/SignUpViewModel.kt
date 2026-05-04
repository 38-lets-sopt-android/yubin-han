package com.example.letssopt.presentation.onboarding.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.core.util.AuthValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val userRepository: AuthRepository = AuthRepository.getInstance()
) : ViewModel() {

    var uiState by mutableStateOf(SignUpContract.SignUpUiState())
        private set
    private val _effect = Channel<SignUpContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun signUp() {
        val errorType = AuthValidator.validateSignUp(
            uiState.emailText,
            uiState.pwText,
            uiState.pwConfirmText
        )

        viewModelScope.launch {
            if (errorType != null) {
                _effect.send(SignUpContract.Effect.ShowToast(errorType.errorMessage))
            } else {
                userRepository.signUp(uiState.emailText, uiState.pwText)
                    .onSuccess {
                        uiState = SignUpContract.SignUpUiState()
                        _effect.send(SignUpContract.Effect.ShowToast("회원가입이 완료되었습니다."))
                        _effect.send(SignUpContract.Effect.NavigateToNext)
                    }
                    .onFailure { error ->
                        _effect.send(SignUpContract.Effect.ShowToast(error.message ?: "회원가입 실패"))
                    }
            }
        }
    }


    fun updateEmail(text: String) {
        uiState = uiState.copy(emailText = text)
    }

    fun updatePw(text: String) {
        uiState = uiState.copy(pwText = text)
    }

    fun updatePwConfirm(text: String) {
        uiState = uiState.copy(pwConfirmText = text)
    }
}
