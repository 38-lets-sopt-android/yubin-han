package com.example.letssopt.presentation.onboarding.signup

class SignUpContract {
    data class SignUpUiState(
        val emailText: String = "",
        val pwText: String = "",
        val pwConfirmText: String = ""
    ) {
        val isAllEntered: Boolean
            get() = emailText.isNotBlank() && pwText.isNotBlank() && pwConfirmText.isNotBlank()
    }

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
        object NavigateToNext : Effect
    }
}
