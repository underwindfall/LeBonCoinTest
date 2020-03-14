package com.qifan.leboncointest.data

import com.qifan.leboncointest.data.datasource.local.AlbumLocalData
import com.qifan.leboncointest.data.datasource.remote.AlbumApiData
import com.qifan.leboncointest.data.extension.processApiResponse
import com.qifan.leboncointest.data.mapper.toLocalData
import com.qifan.leboncointest.data.mapper.toModel
import com.qifan.leboncointest.domain.exception.LeBonCoinException
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import io.reactivex.Single
import io.reactivex.SingleTransformer
import retrofit2.Response

fun transformLocalDataToModel(): SingleTransformer<
        List<AlbumLocalData>,
        Results<List<AlbumModel>>> {
    return SingleTransformer { upstream ->
        upstream
            .flatMap { localData ->
                if (localData.isNullOrEmpty()) {
                    Single.just(Results.failure(LeBonCoinException.EmptyException("Data base is empty")))
                } else {
                    Single.just(Results.success(result = localData.toModel()))
                }
            }
    }
}


fun transformRemoteDataToModel(): SingleTransformer<Response<List<AlbumApiData>>, Results<List<AlbumLocalData>>> {
    return SingleTransformer { upstream ->
        upstream.flatMap { apiResponse ->
            when (val apiData = processApiResponse(apiResponse)) {
                is Results.Success -> Single.just(Results.success(result = apiData.data.map { it.toLocalData() }))
                is Results.Failure -> Single.just(Results.failure(error = apiData.error))
            }
        }
    }
}