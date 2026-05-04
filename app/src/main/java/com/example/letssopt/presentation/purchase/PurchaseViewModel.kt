package com.example.letssopt.presentation.purchase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.model.purchase.PurchaseContent
import com.example.letssopt.core.data.repository.api.PurchaseRepository
import com.example.letssopt.core.data.repository.api.StorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class PurchaseViewModel(
    private val purchaseRepository: PurchaseRepository,
    private val storageRepository: StorageRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PurchaseContract.UiState())
    val uiState: StateFlow<PurchaseContract.UiState> = _uiState.asStateFlow()

    private val _effect = Channel<PurchaseContract.Effect>()
    val effect = _effect.receiveAsFlow()

    init {
        _uiState.update { it.copy(purchaseItems = purchaseRepository.getPurchaseItems()) }
    }

    fun storeItem(item: PurchaseContent) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val entity = StoredItemEntity(
                    title = item.title,
                    imageRes = item.image,
                )
                storageRepository.insertItem(entity)
                _effect.send(PurchaseContract.Effect.ShowToast("보관함에 저장되었습니다!"))
            } catch (e: Exception) {
                _effect.send(PurchaseContract.Effect.ShowToast("저장에 실패했습니다."))
            }
        }
    }
}
