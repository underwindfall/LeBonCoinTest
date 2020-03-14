package com.qifan.leboncointest.domain.usecase.album

import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.usecase.UseCase
import io.reactivex.Single

interface GetAlbumUseCase : UseCase {
    fun getAlbums(): Single<List<AlbumModel>>
}