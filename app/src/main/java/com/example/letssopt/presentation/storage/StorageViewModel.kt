package com.example.letssopt.presentation.storage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.data.local.entity.StoredItemEntity
import com.example.letssopt.data.repository.api.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StorageViewModel(
    private val storageRepository: StorageRepository = StorageRepository.getInstance()
) : ViewModel() {
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
            _effect.send(StorageContract.Effect.ShowToast("콘텐츠를 삭제했습니다."))
        }
    }
}

