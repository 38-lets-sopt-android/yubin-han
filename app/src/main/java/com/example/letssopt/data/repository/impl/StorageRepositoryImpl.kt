package com.example.letssopt.data.repository.impl
import com.example.letssopt.data.local.dao.StoredItemDao
import com.example.letssopt.data.local.entity.StoredItemEntity
import com.example.letssopt.data.repository.api.StorageRepository
import kotlinx.coroutines.flow.Flow

class StorageRepositoryImpl(private val storedItemDao: StoredItemDao) : StorageRepository {
    override fun getAllStoredItems(): Flow<List<StoredItemEntity>> = storedItemDao.getAllStoredItems()
    override suspend fun insertItem(item: StoredItemEntity) = storedItemDao.insertItem(item)
    override suspend fun deleteItem(item: StoredItemEntity) = storedItemDao.deleteItem(item)
}
