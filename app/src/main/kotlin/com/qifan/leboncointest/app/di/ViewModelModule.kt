package com.qifan.leboncointest.app.di

import com.qifan.leboncointest.app.ui.album.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AlbumViewModel(get())
    }
}