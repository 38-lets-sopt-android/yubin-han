package com.example.letssopt.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.letssopt.core.data.local.entity.StoredItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoredItemDao {
    @Insert
    suspend fun insertItem(item: StoredItemEntity)

    @Query("SELECT * FROM stored_items")
    fun getAllStoredItems(): Flow<List<StoredItemEntity>>

    @Delete
    suspend fun deleteItem(item: StoredItemEntity)
}
