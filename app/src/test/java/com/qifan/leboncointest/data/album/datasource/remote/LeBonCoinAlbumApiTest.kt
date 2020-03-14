package com.qifan.leboncointest.data.album.datasource.remote

import com.qifan.leboncointest.data.MockAlbumServer
import com.qifan.leboncointest.data.datasource.remote.AlbumApiData
import com.qifan.leboncointest.data.datasource.remote.LeBonCoinAlbumApi
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class LeBonCoinAlbumApiTest {
    private val mockService by lazy { MockAlbumServer(LeBonCoinAlbumApi::class.java) }
    private lateinit var request: RecordedRequest

    @Before
    fun setUp() {
        mockService.enqueueResponse(200, "albums.json")
    }

    @After
    fun tearDown(){
        mockService.getMockServer().shutdown()
    }

    @Test
    fun when_le_boncoin_api_send_a_request_return_200() {

        val response = mockService.get()
            .getAlbums()
            .blockingGet()

        Assert.assertTrue(response.isSuccessful)
    }

    @Test
    fun when_le_bon_api_send_a_request_should_serialize_it() {
        val testSubscriber:TestObserver<Response<List<AlbumApiData>>> = TestObserver()
        mockService.get().getAlbums().subscribe(testSubscriber)
        request = mockService.takeRequest()
        Assert.assertEquals(request.method, "GET")
        Assert.assertEquals(request.path, "/img/shared/technical-test.json")
    }


}