package com.example.letssopt.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.letssopt.core.data.local.dao.StoredItemDao
import com.example.letssopt.core.data.local.entity.StoredItemEntity

@Database(entities = [StoredItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storedItemDao(): StoredItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "watcha_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
