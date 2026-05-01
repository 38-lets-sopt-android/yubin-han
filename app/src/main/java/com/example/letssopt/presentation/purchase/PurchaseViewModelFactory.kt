package com.example.letssopt.presentation.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.repository.api.PurchaseRepository
import com.example.letssopt.core.data.repository.api.StorageRepository

class PurchaseViewModelFactory(private val dao: StoredItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PurchaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PurchaseViewModel(
                purchaseRepository = PurchaseRepository.getInstance(),
                storageRepository = StorageRepository.getInstance(dao)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
