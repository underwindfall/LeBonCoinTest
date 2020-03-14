package com.qifan.leboncointest.app.extension

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.mainThread(): Flowable<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.mainThread(): Single<T> {
    return this@mainThread
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.computation(): Flowable<T> {
    return this@computation
        .subscribeOn(Schedulers.computation())
}

fun <T> Flowable<T>.io(): Flowable<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}

fun <T> Single<T>.io(): Single<T> {
    return this@io
        .subscribeOn(Schedulers.io())
}