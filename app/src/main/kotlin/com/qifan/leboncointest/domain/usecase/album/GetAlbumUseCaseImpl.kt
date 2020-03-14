package com.qifan.leboncointest.domain.usecase.album

import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.repository.GetAlbumRepository
import io.reactivex.Single

class GetAlbumUseCaseImpl(private val repository: GetAlbumRepository) : GetAlbumUseCase {
    override fun getAlbums(): Single<List<AlbumModel>> {
        return repository.getAlbums()
    }
}