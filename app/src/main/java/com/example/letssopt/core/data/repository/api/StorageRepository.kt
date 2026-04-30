package com.example.letssopt.core.data.repository.api

import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import com.example.letssopt.core.data.repository.impl.StorageRepositoryImpl
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getAllStoredItems(): Flow<List<StoredItemEntity>>
    suspend fun insertItem(item: StoredItemEntity)
    suspend fun deleteItem(item: StoredItemEntity)

    companion object {
        fun getInstance(dao: StoredItemDao): StorageRepository = StorageRepositoryImpl(dao)
    }
}
