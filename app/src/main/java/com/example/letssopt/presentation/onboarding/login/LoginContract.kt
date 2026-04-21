package com.example.letssopt.presentation.onboarding.login


class LoginContract {
    data class UiState(
        val emailText: String = "",
        val pwText: String = ""
    ) {
        val isLogInEnabled: Boolean
            get() = emailText.isNotBlank() && pwText.isNotBlank()
    }

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
        object NavigateToMain : Effect
    }
}
