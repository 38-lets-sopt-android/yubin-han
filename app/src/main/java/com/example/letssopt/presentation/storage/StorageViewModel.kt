package com.example.letssopt.presentation.storage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.repository.api.StorageRepository
import com.example.letssopt.core.data.repository.impl.StorageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StorageViewModel(private val storageRepository: StorageRepository) : ViewModel() {
    val storedItems: StateFlow<List<StoredItemEntity>> = storageRepository.getAllStoredItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList(),
        )

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
            return StorageViewModel(StorageRepositoryImpl(dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
