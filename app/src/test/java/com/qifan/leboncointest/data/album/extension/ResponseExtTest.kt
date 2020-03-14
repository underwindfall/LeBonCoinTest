package com.qifan.leboncointest.data.album.extension

import com.qifan.leboncointest.data.datasource.local.LeBonCoinDatabase
import com.qifan.leboncointest.data.extension.processApiResponse
import com.qifan.leboncointest.domain.exception.LeBonCoinException
import com.qifan.leboncointest.domain.model.Results
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ResponseExtTest {
    @Mock
    private lateinit var mockResponse: Response<Any>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun test_when_response_contain_error_return_failure() {
        given(mockResponse.isSuccessful).willReturn(false)
        Assert.assertTrue(
            processApiResponse(mockResponse) is Results.Failure
        )
    }

    @Test
    fun test_when_response_success_return_success() {
        given(mockResponse.isSuccessful).willReturn(true)
        given(mockResponse.body()).willReturn(Any())
        Assert.assertTrue(
            processApiResponse(mockResponse) is Results.Success
        )
    }


    @Test
    fun test_when_response_contain_body_null_return_failure() {
        given(mockResponse.body()).willReturn(null)
        Assert.assertTrue(
            (processApiResponse(mockResponse) as Results.Failure).error is LeBonCoinException.NetworkException
        )
    }
}