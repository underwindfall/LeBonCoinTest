package com.qifan.leboncointest.domain.di

import com.qifan.leboncointest.domain.di.repository.repositoryModule
import com.qifan.leboncointest.domain.di.usecase.useCaseModule

val domainModule = repositoryModule + useCaseModule