package com.qifan.leboncointest.data.datasource.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface LeBonCoinAlbumApi {
    @GET("img/shared/technical-test.json")
    fun getAlbums(): Single<Response<List<AlbumApiData>>>

    companion object {
        private const val HOST = "static.leboncoin.fr"
        const val ENDPOINT = "https://$HOST"
    }
}