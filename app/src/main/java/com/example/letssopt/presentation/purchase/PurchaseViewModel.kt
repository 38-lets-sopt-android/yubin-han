package com.example.letssopt.presentation.purchase

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.model.purchase.PurchaseContent
import com.example.letssopt.core.data.repository.api.PurchaseRepository
import com.example.letssopt.core.data.repository.api.StorageRepository
import com.example.letssopt.core.data.repository.impl.PurchaseRepositoryImpl
import com.example.letssopt.core.data.repository.impl.StorageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class PurchaseViewModel(
    private val purchaseRepository: PurchaseRepository,
    private val storageRepository: StorageRepository
) : ViewModel() {
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()
    private val _purchaseItems = mutableStateListOf<PurchaseContent>().apply {
        addAll(purchaseRepository.getPurchaseItems())
    }
    val purchaseItems: List<PurchaseContent> = _purchaseItems

    fun storeItem(item: PurchaseContent) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val entity = StoredItemEntity(
                    title = item.title,
                    imageRes = item.image,
                )
                storageRepository.insertItem(entity)
                _toastEvent.emit("보관함에 저장되었습니다!")
            } catch (e: Exception) {
                _toastEvent.emit("저장에 실패했습니다.")
            }
        }
    }
}

class PurchaseViewModelFactory(private val dao: StoredItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PurchaseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PurchaseViewModel(
                purchaseRepository = PurchaseRepositoryImpl(),
                storageRepository = StorageRepositoryImpl(dao)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
