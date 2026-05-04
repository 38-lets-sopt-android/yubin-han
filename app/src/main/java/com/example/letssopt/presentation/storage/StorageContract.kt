package com.example.letssopt.presentation.storage

import com.example.letssopt.core.data.local.entity.StoredItemEntity

interface StorageContract {
    data class UiState(
        val storedItems: List<StoredItemEntity> = emptyList()
    )

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
    }
}
