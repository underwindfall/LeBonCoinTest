package com.qifan.leboncointest.domain.di.repository

import com.qifan.leboncointest.data.GetAlbumRepositoryImpl
import com.qifan.leboncointest.domain.repository.GetAlbumRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<GetAlbumRepository> {
        GetAlbumRepositoryImpl(get(), get())
    }
}