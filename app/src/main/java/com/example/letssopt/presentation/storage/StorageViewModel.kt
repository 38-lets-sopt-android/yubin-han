package com.example.letssopt.presentation.storage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.repository.api.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StorageViewModel(private val storageRepository: StorageRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(StorageContract.UiState())
    val uiState: StateFlow<StorageContract.UiState> = _uiState.asStateFlow()

    private val _effect = Channel<StorageContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        observeStoredItems()
    }

    private fun observeStoredItems() {
        viewModelScope.launch {
            storageRepository.getAllStoredItems().collect { items ->
                _uiState.update { it.copy(storedItems = items) }
            }
        }
    }

    fun deleteItem(item: StoredItemEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            storageRepository.deleteItem(item)
        }
    }
}

class StorageViewModelFactory(private val dao: StoredItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StorageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StorageViewModel(StorageRepository.getInstance(dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
