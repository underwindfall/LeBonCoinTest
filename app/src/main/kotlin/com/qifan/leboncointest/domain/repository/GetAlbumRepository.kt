package com.qifan.leboncointest.domain.repository

import com.qifan.leboncointest.domain.model.AlbumModel
import io.reactivex.Single

interface GetAlbumRepository {
    fun getAlbums(): Single<List<AlbumModel>>
}