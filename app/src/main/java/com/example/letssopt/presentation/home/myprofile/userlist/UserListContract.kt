package com.example.letssopt.presentation.home.myprofile.userlist

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.data.model.home.myprofile.UserProfile
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

interface UserListContract {
    @Immutable
    data class UiState(
        val isLoading: Boolean = false,
        val users: PersistentList<UserProfile> = persistentListOf()
    )

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
    }
}
