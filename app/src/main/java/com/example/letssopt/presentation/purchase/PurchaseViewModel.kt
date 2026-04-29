package com.example.letssopt.presentation.purchase

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.letssopt.R
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.model.purchase.PurchaseContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class PurchaseViewModel(private val storedItemDao: StoredItemDao) : ViewModel() {
    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()
    private val _purchaseItems = mutableStateListOf(
        PurchaseContent("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
        PurchaseContent("기묘한 이야기5", R.drawable.img_poster_stranger_things),
        PurchaseContent("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
        PurchaseContent("주토피아2", R.drawable.img_poster_jootopia2),
        PurchaseContent("모아나2", R.drawable.img_poster_moana2),
        PurchaseContent("어벤져스: 둠스데이", R.drawable.img_poster_avengers_doomsday)
    )
    val purchaseItems: List<PurchaseContent> = _purchaseItems

    fun storeItem(item: PurchaseContent) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val entity = StoredItemEntity(
                    title = item.title,
                    imageRes = item.image
                )
                storedItemDao.insertItem(entity)
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
            return PurchaseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
