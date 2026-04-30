package com.example.letssopt.core.data.repository.api

import com.example.letssopt.core.data.local.entity.StoredItemEntity
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getAllStoredItems(): Flow<List<StoredItemEntity>>
    suspend fun insertItem(item: StoredItemEntity)
    suspend fun deleteItem(item: StoredItemEntity)
}
