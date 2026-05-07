package com.example.letssopt.data.repository.api

import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.repository.impl.StorageRepositoryImpl
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getAllStoredItems(): Flow<List<StoredItemEntity>>
    suspend fun insertItem(item: StoredItemEntity)
    suspend fun deleteItem(item: StoredItemEntity)

    companion object {
        private lateinit var _instance: StorageRepository

        fun init(dao: StoredItemDao) {
            _instance = StorageRepositoryImpl(dao)
        }

        fun getInstance(): StorageRepository = _instance
    }
}
