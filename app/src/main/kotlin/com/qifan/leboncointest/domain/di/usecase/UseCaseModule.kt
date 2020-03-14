package com.qifan.leboncointest.domain.di.usecase

import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCase
import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetAlbumUseCase> { GetAlbumUseCaseImpl(get()) }
}