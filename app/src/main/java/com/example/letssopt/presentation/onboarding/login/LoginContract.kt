package com.example.letssopt.presentation.onboarding.login


class LoginContract {
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
