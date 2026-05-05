package com.example.letssopt.presentation.onboarding.signup

import androidx.compose.runtime.Immutable

interface SignUpContract {
    @Immutable
    data class SignUpUiState(
        val idText: String = "",
        val pwText: String = "",
        val pwConfirmText: String = "",
        val nameText: String = "",
        val emailText: String = "",
        val ageText: String = "",
        val partText: String = "",
    ) {
        val isAllEntered: Boolean
            get() = idText.isNotBlank() && pwText.isNotBlank() && pwConfirmText.isNotBlank() && nameText.isNotBlank() && emailText.isNotBlank() && ageText.isNotBlank() && partText.isNotBlank()
    }
    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
        object NavigateToNext : Effect
    }
}
