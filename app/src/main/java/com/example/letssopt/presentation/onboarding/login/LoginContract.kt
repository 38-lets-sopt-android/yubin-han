package com.example.letssopt.presentation.onboarding.login

import androidx.compose.runtime.Immutable


interface LoginContract {
    @Immutable
    data class UiState(
        val idText: String = "",
        val pwText: String = ""
    ) {
        val isLogInEnabled: Boolean
            get() = idText.isNotBlank() && pwText.isNotBlank()
    }
    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
        object NavigateToMain : Effect
    }
}
