package com.example.letssopt.presentation.storage


import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.letssopt.R
import com.example.letssopt.model.StorageContent

class StorageViewModel : ViewModel() {
    private val _storageItems = mutableStateListOf(
        StorageContent("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
        StorageContent("기묘한 이야기", R.drawable.img_poster_stranger_things),
        StorageContent("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
        StorageContent("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
        StorageContent("기묘한 이야기", R.drawable.img_poster_stranger_things),
        StorageContent("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
    )
    val storageItems: List<StorageContent> = _storageItems
    fun removeItem(item: StorageContent) {
        _storageItems.remove(item)
    }
}
