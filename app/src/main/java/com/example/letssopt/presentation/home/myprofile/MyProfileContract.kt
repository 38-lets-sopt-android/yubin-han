package com.example.letssopt.presentation.home.myprofile

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.data.model.home.myprofile.UserProfile

interface MyProfileContract {
    @Immutable
    data class UiState(
        val isLoading: Boolean = false,
        val myProfile: UserProfile? = null,
    )

    sealed interface Effect {
        data object NavigateToUserList : Effect
    }
}
