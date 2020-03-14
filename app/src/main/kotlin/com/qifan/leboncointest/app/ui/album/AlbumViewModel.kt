package com.qifan.leboncointest.app.ui.album

import com.qifan.leboncointest.app.base.viewmodel.LoadingViewModel
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCase
import io.reactivex.Single

class AlbumViewModel(private val getAlbumUseCase: GetAlbumUseCase) :
    LoadingViewModel<Results<List<AlbumModel>>>() {

    fun getAlbums(): Single<Results<List<AlbumModel>>> {
        return getAlbumUseCase.getAlbums()
            .bindLoadingState(data)
    }

}
