package com.qifan.leboncointest.app.di

import com.qifan.leboncointest.data.di.dataModule
import com.qifan.leboncointest.domain.di.domainModule

val appModule = dataModule + domainModule + viewModule