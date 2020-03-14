package com.qifan.leboncointest.data.mapper

import com.qifan.leboncointest.data.datasource.local.AlbumLocalData
import com.qifan.leboncointest.data.datasource.remote.AlbumApiData
import com.qifan.leboncointest.data.entity.AlbumEntity
import com.qifan.leboncointest.domain.model.AlbumModel

fun AlbumApiData.toLocalData(): AlbumLocalData {
    return AlbumLocalData(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}

fun AlbumLocalData.toEntity(): AlbumEntity {
    return AlbumEntity(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}


fun AlbumEntity.toModel(): AlbumModel {
    return AlbumModel(
        id = id,
        albumId = albumId,
        title = title,
        url = url,
        thumbnailUrl = thumbnailUrl
    )
}


fun List<AlbumLocalData>.toModel(): List<AlbumModel> {
    return this
        .map {
            it.toEntity()
                .toModel()
        }

}