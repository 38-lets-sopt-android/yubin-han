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
    private val authRepository: AuthRepository = AuthRepository.getInstance()
) : ViewModel() {

    var uiState by mutableStateOf(SignUpContract.SignUpUiState())
        private set
    private val _effect = Channel<SignUpContract.Effect>()
    val effect = _effect.receiveAsFlow()

    fun signUp() {
        val errorType = AuthValidator.validateSignUp(
            email = uiState.emailText,
            pw = uiState.pwText,
            pwConfirm = uiState.pwConfirmText,
            age = uiState.ageText,
            part = uiState.partText
        )

        viewModelScope.launch {
            if (errorType != null) {
                _effect.send(SignUpContract.Effect.ShowToast(errorType.errorMessage))
            } else {
                authRepository.signUp(
                    id = uiState.idText,
                    pw = uiState.pwText,
                    name = uiState.nameText,
                    email = uiState.emailText,
                    age = uiState.ageText.toIntOrNull() ?: 0,
                    part = uiState.partText
                )
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


    fun updateId(text: String) {
        uiState = uiState.copy(idText = text)
    }

    fun updatePw(text: String) {
        uiState = uiState.copy(pwText = text)
    }

    fun updatePwConfirm(text: String) {
        uiState = uiState.copy(pwConfirmText = text)
    }

    fun updateName(text: String) {
        uiState = uiState.copy(nameText = text)
    }

    fun updateEmail(text: String) {
        uiState = uiState.copy(emailText = text)
    }

    fun updateAge(text: String) {
        uiState = uiState.copy(ageText = text)
    }

    fun updatePart(text: String) {
        uiState = uiState.copy(partText = text)
    }
}
