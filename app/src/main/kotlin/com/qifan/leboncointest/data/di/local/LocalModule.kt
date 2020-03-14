package com.qifan.leboncointest.data.di.local

import com.qifan.leboncointest.data.datasource.local.LeBonCoinDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single {
        LeBonCoinDatabase.getInstance(androidApplication())
    }

    single {
        get<LeBonCoinDatabase>().albumDao()
    }
}