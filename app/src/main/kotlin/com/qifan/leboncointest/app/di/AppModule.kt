package com.qifan.leboncointest.app.di

import com.qifan.leboncointest.data.di.dataModule
import com.qifan.leboncointest.domain.di.domainModule
import org.koin.core.module.Module

val appModule = dataModule+ domainModule