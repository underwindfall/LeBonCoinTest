package com.qifan.leboncointest.data.album.di

import com.qifan.leboncointest.data.album.di.local.localModule
import com.qifan.leboncointest.data.album.di.remote.remoteModule

val dataModule = localModule + remoteModule