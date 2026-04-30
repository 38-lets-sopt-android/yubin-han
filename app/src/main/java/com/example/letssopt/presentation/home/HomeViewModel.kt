package com.example.letssopt.presentation.home


import androidx.lifecycle.ViewModel
import com.example.letssopt.core.data.repository.api.HomeRepository
import com.example.letssopt.core.data.repository.impl.HomeRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val homeRepository: HomeRepository = HomeRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeContract.UiState())
    val uiState: StateFlow<HomeContract.UiState> = _uiState.asStateFlow()

    init {
        fetchHomeItems()
    }

    fun fetchHomeItems() {
        val items = homeRepository.getHomeItems()
        _uiState.update { it.copy(homeItems = items) }
    }

}
