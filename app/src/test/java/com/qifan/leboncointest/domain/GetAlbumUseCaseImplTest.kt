package com.qifan.leboncointest.domain

import android.content.Context
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import com.qifan.leboncointest.domain.repository.GetAlbumRepository
import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCase
import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCaseImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GetAlbumUseCaseImplTest {
    @Mock
    private lateinit var repository: GetAlbumRepository

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var mockResult: Results<List<AlbumModel>>

    private lateinit var getAlbumUseCase: GetAlbumUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAlbumUseCase = GetAlbumUseCaseImpl(repository, context)
    }

    @Test
    fun when_getAlbumUseCase_should_call_repository() {
        given(repository.getAlbums(context)).willReturn(
            Single.just(mockResult)
        )
        getAlbumUseCase.getAlbums()
        verify(repository)
    }
}