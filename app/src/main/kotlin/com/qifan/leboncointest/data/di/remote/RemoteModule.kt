package com.qifan.leboncointest.data.di.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.qifan.leboncointest.data.datasource.remote.LeBonCoinAlbumApi
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val remoteModule = module {

    single<Gson>(createdAtStart = true) {
        GsonBuilder().create()
    }

    single<CallAdapter.Factory>(createdAtStart = true) {
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }


    single<Converter.Factory>(createdAtStart = true) {
        GsonConverterFactory.create()
    }

    single<OkHttpClient>(createdAtStart = true) {
        OkHttpClient.Builder()
            .build()
    }

    single(createdAtStart = true) {
        Retrofit.Builder()
            .baseUrl(LeBonCoinAlbumApi.ENDPOINT)
            .addConverterFactory(get())
            .addCallAdapterFactory(get())
            .client(get())
            .build()
    }

    single(createdAtStart = true) {
        get<Retrofit>().create(LeBonCoinAlbumApi::class.java)
    }
}