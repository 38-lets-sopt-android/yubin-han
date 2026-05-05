package com.example.letssopt.presentation.storage

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.data.local.entity.StoredItemEntity

interface StorageContract {
    @Immutable
    data class UiState(
        val storedItems: List<StoredItemEntity> = emptyList()
    )

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
    }
}
