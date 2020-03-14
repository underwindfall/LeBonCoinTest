package com.qifan.leboncointest.domain.usecase.album

import android.content.Context
import com.qifan.leboncointest.app.extension.io
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import com.qifan.leboncointest.domain.repository.GetAlbumRepository
import io.reactivex.Single

class GetAlbumUseCaseImpl(private val repository: GetAlbumRepository) : GetAlbumUseCase {
    override fun getAlbums(context: Context): Single<Results<List<AlbumModel>>> {
        return repository.getAlbums(context)
            .io()
    }
}