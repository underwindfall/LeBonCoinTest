package com.qifan.leboncointest.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumLocalData::class], version = 1, exportSchema = false)
abstract class LeBonCoinDatabase : RoomDatabase() {


    companion object {
        private const val DATABASE_NAME = "LeBonCoin.db"

        @Volatile
        private var instance: LeBonCoinDatabase? = null

        fun getInstance(context: Context): LeBonCoinDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): LeBonCoinDatabase {
            return Room.databaseBuilder(
                    context,
                    LeBonCoinDatabase::class.java,
                    DATABASE_NAME
                )
                .build()
        }
    }

    abstract fun albumDao(): AlbumDao
}