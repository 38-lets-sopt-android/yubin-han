package com.example.letssopt.presentation.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.repository.api.StorageRepository

class StorageViewModelFactory(private val dao: StoredItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StorageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StorageViewModel(StorageRepository.getInstance(dao)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
