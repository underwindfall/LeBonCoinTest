package com.qifan.leboncointest.data

import android.content.Context
import com.qifan.leboncointest.data.datasource.local.AlbumDao
import com.qifan.leboncointest.data.datasource.remote.LeBonCoinAlbumApi
import com.qifan.leboncointest.data.params.NetworkParams
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import com.qifan.leboncointest.domain.repository.GetAlbumRepository
import io.reactivex.Completable
import io.reactivex.Single

class GetAlbumRepositoryImpl(
    private val remote: LeBonCoinAlbumApi,
    private val local: AlbumDao,
    private val networkParams: NetworkParams
) : GetAlbumRepository {

    override fun getAlbums(context: Context): Single<Results<List<AlbumModel>>> {
        return if (networkParams.getNetworkAvailable(context)) {
            remote.getAlbums()
                .compose(transformRemoteDataToModel())
                .flatMapCompletable { apiResult ->
                    when (apiResult) {
                        is Results.Success -> local.insertAll(apiResult.data)
                        else -> Completable.complete()
                    }
                }
                .andThen(local.getAll())
                .compose(transformLocalDataToModel())
        } else {
            local.getAll()
                .compose(transformLocalDataToModel())
        }
    }

}