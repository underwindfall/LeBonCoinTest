package com.qifan.leboncointest.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlbumDao {
    @Query("SELECT * FROM ${AlbumLocalData.TABLE_NAME}")
    fun getAll(): Single<List<AlbumLocalData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<AlbumLocalData>): Completable
}