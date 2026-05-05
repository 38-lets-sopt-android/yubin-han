package com.example.letssopt.presentation.purchase

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.data.model.purchase.PurchaseContent

interface PurchaseContract {
    @Immutable
    data class UiState(
        val purchaseItems: List<PurchaseContent> = emptyList()
    )

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
    }
}
