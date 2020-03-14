package com.qifan.leboncointest.data.di

import com.qifan.leboncointest.data.di.local.localModule
import com.qifan.leboncointest.data.di.remote.remoteModule

val dataModule = localModule + remoteModule