package com.qifan.leboncointest.data.album.respository

import android.content.Context
import com.qifan.leboncointest.data.GetAlbumRepositoryImpl
import com.qifan.leboncointest.data.datasource.local.AlbumDao
import com.qifan.leboncointest.data.datasource.local.AlbumLocalData
import com.qifan.leboncointest.data.datasource.remote.AlbumApiData
import com.qifan.leboncointest.data.datasource.remote.LeBonCoinAlbumApi
import com.qifan.leboncointest.data.params.NetworkParams
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class GetAlbumRepositoryImplTest {
    @Mock
    private lateinit var mockApi: LeBonCoinAlbumApi

    @Mock
    private lateinit var mockLocal: AlbumDao

    @Mock
    private lateinit var mockNetworkParams: NetworkParams


    private lateinit var getAlbumRepositoryImpl: GetAlbumRepositoryImpl

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAlbumRepositoryImpl = GetAlbumRepositoryImpl(
            remote = mockApi,
            local = mockLocal,
            networkParams = mockNetworkParams
        )
    }

    @Test
    fun test_when_api_get_albums_success() {
        val listApiData = listOf(
            AlbumApiData(
                id = 0,
                albumId = 1,
                title = "title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )
        )

        val listLocalData = listOf(
            AlbumLocalData(
                id = 0,
                albumId = 1,
                title = "title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )
        )
        given(mockApi.getAlbums())
            .willReturn(
                Single.just(Response.success(listApiData))
            )
        given(mockLocal.insertAll(listLocalData))
            .willReturn(Completable.complete())
        given(mockLocal.getAll())
            .willReturn(
                Single.just(
                    listLocalData
                )
            )
        given(mockNetworkParams.getNetworkAvailable(context)).willReturn(true)
        val testObserver = TestObserver<Results<List<AlbumModel>>>()
        getAlbumRepositoryImpl.getAlbums(context)
            .subscribe(testObserver)
    }


    @Test
    fun test_when_api_get_albums_network_fail_invoke_local() {
        given(mockNetworkParams.getNetworkAvailable(context)).willReturn(false)
        val listApiData = listOf(
            AlbumApiData(
                id = 0,
                albumId = 1,
                title = "title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )
        )

        val listLocalData = listOf(
            AlbumLocalData(
                id = 0,
                albumId = 1,
                title = "title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            )
        )
        given(mockApi.getAlbums())
            .willReturn(
                Single.just(Response.success(listApiData))
            )
        given(mockLocal.insertAll(listLocalData))
            .willReturn(Completable.complete())
        given(mockLocal.getAll())
            .willReturn(
                Single.just(
                    listLocalData
                )
            )
        getAlbumRepositoryImpl.getAlbums(context)

        Mockito.verify(mockLocal).getAll()
    }


}