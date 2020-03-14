package com.qifan.leboncointest.domain.repository

import android.content.Context
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import io.reactivex.Single

interface GetAlbumRepository {
    fun getAlbums(context: Context): Single<Results<List<AlbumModel>>>
}