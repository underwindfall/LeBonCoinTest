package com.qifan.leboncointest.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AlbumLocalData.TABLE_NAME)
data class AlbumLocalData(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_ALBUM_ID)
    val albumId: Int,
    @ColumnInfo(name = COLUMN_title)
    val title: String,
    @ColumnInfo(name = COLUMN_URL)
    val url: String,
    @ColumnInfo(name = COLUMN_THUMBNAIL_URL)
    val thumbnailUrl: String
) {
    companion object {
        const val TABLE_NAME = "leboncoin_album_table"
        const val COLUMN_ID = "id"
        const val COLUMN_ALBUM_ID = "albumId"
        const val COLUMN_title = "title"
        const val COLUMN_URL = "url"
        const val COLUMN_THUMBNAIL_URL = "thumbnailUrl"
    }
}