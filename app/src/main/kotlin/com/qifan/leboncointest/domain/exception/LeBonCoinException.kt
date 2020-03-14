package com.qifan.leboncointest.domain.exception

sealed class LeBonCoinException(message: String? = null, throwable: Throwable? = null) :
    Exception(message, throwable) {
    class GeneralException(throwable: Throwable?) : LeBonCoinException(throwable = throwable)
    class EmptyException(message: String? = "Empty Data Found") : LeBonCoinException(message)
    class NetworkException(message: String? = null, throwable: Throwable? = null) :
        LeBonCoinException(message, throwable)
}