package com.qifan.leboncointest.app.extension

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

private const val THROTTLE_DURATION = 1L

fun <T> Flowable<T>.throttleDefault(duration: Long = THROTTLE_DURATION): Flowable<T> {
    return this@throttleDefault
        .throttleFirst(duration, TimeUnit.SECONDS)
}
