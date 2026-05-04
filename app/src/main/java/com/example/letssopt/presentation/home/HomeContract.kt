package com.example.letssopt.presentation.home

import com.example.letssopt.core.data.model.home.MainHomeItem

interface HomeContract {
    data class UiState(
        val homeItems: List<MainHomeItem> = emptyList()
    )
}
