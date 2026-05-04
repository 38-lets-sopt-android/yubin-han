package com.example.letssopt.presentation.purchase

import com.example.letssopt.core.data.model.purchase.PurchaseContent

interface PurchaseContract {
    data class UiState(
        val purchaseItems: List<PurchaseContent> = emptyList()
    )

    sealed interface Effect {
        data class ShowToast(val message: String) : Effect
    }
}
