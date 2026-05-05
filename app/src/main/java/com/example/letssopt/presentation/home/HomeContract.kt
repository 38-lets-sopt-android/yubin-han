package com.example.letssopt.presentation.home

import androidx.compose.runtime.Immutable
import com.example.letssopt.core.data.model.home.MainHomeItem

interface HomeContract {
    @Immutable
    data class UiState(
        val homeItems: List<MainHomeItem> = emptyList()
    )
}
