package com.qifan.leboncointest.data

import com.qifan.leboncointest.app.extension.io
import com.qifan.leboncointest.data.datasource.local.AlbumDao
import com.qifan.leboncointest.data.datasource.local.AlbumLocalData
import com.qifan.leboncointest.data.datasource.remote.LeBonCoinAlbumApi
import com.qifan.leboncointest.data.mapper.toEntity
import com.qifan.leboncointest.data.mapper.toLocalData
import com.qifan.leboncointest.data.mapper.toModel
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.repository.GetAlbumRepository
import io.reactivex.Single

class GetAlbumRepositoryImpl(
    private val remote: LeBonCoinAlbumApi,
    private val local: AlbumDao
) : GetAlbumRepository {
    override fun getAlbums(): Single<List<AlbumModel>> {
        return remote.getAlbums()
            .io()
            .map { apiData -> apiData.map { it.toLocalData() } }
            .flatMapCompletable { local.insertAll(it) }
            .toSingleDefault(emptyList<AlbumLocalData>())
            .flatMap { local.getAll() }
            .map { localData -> localData.map { it.toEntity() } }
            .map { entityData -> entityData.map { it.toModel() } }
    }
}