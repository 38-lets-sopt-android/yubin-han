package com.example.letssopt.presentation.onboarding.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.repository.api.AuthRepository
import com.example.letssopt.core.util.AuthValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val authRepository: AuthRepository = AuthRepository.getInstance()
) : ViewModel() {


    private val _uiState = MutableStateFlow(SignUpContract.SignUpUiState())
    val uiState: StateFlow<SignUpContract.SignUpUiState> = _uiState.asStateFlow()

    private val _effect = Channel<SignUpContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun signUp() {
        val currentState = _uiState.value
        val errorType = AuthValidator.validateSignUp(
            email = currentState.emailText,
            pw = currentState.pwText,
            pwConfirm = currentState.pwConfirmText,
            age = currentState.ageText,
            part = currentState.partText
        )

        viewModelScope.launch {
            if (errorType != null) {
                _effect.send(SignUpContract.Effect.ShowToast(errorType.errorMessage))
            } else {
                authRepository.signUp(
                    id = currentState.idText,
                    pw = currentState.pwText,
                    name = currentState.nameText,
                    email = currentState.emailText,
                    age = currentState.ageText.toIntOrNull() ?: 0,
                    part = currentState.partText
                )
                    .onSuccess {
                        _uiState.update { SignUpContract.SignUpUiState() }
                        _effect.send(SignUpContract.Effect.ShowToast("회원가입이 완료되었습니다."))
                        _effect.send(SignUpContract.Effect.NavigateToNext)
                    }
                    .onFailure { error ->
                        _effect.send(SignUpContract.Effect.ShowToast(error.message ?: "회원가입 실패"))
                    }
            }
        }
    }


    fun updateId(text: String) {
        _uiState.update { it.copy(idText = text) }
    }

    fun updatePw(text: String) {
        _uiState.update { it.copy(pwText = text) }
    }

    fun updatePwConfirm(text: String) {
        _uiState.update { it.copy(pwConfirmText = text) }
    }

    fun updateName(text: String) {
        _uiState.update { it.copy(nameText = text) }
    }

    fun updateEmail(text: String) {
        _uiState.update { it.copy(emailText = text) }
    }

    fun updateAge(text: String) {
        _uiState.update { it.copy(ageText = text) }
    }

    fun updatePart(text: String) {
        _uiState.update { it.copy(partText = text) }
    }
}
