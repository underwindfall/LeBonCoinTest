package com.qifan.leboncointest.app.album

import com.qifan.leboncointest.app.RxSchedulersOverrideRule
import com.qifan.leboncointest.app.ui.album.AlbumViewModel
import com.qifan.leboncointest.domain.exception.LeBonCoinException
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCase
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AlbumViewModelTest {
    @Rule
    @JvmField
    var testSchedulerRule = RxSchedulersOverrideRule()

    @Mock
    private lateinit var getAlbumUseCase: GetAlbumUseCase

    @Mock
    private lateinit var mockResult: Results<List<AlbumModel>>

    @Mock
    private lateinit var mockSuccessResult: List<AlbumModel>

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = AlbumViewModel(getAlbumUseCase)
    }


    @Test
    fun when_view_model_should_call_use_case() {
        `when`(getAlbumUseCase.getAlbums()).thenReturn(Single.just(mockResult))
        viewModel.getAlbums()
        verify(getAlbumUseCase).getAlbums()
    }

    @Test
    fun when_use_case_return_success_view_model_handle_state_success() {
        `when`(getAlbumUseCase.getAlbums()).thenReturn(Single.just(Results.success(mockSuccessResult)))
        val testObserver = TestObserver<Results<List<AlbumModel>>>()
        viewModel.getAlbums().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it is Results.Success
        }
    }


    @Test
    fun when_use_case_return_fail_view_model_handle_state_fail() {
        `when`(getAlbumUseCase.getAlbums()).thenReturn(
            Single.just(
                Results.failure(
                    LeBonCoinException.NetworkException()
                )
            )
        )
        val testObserver = TestObserver<Results<List<AlbumModel>>>()
        viewModel.getAlbums().subscribe(testObserver)
        testObserver.assertNoErrors()
        testObserver.assertValue {
            it is Results.Failure
        }
    }


    @Test
    fun when_use_case_return_loading_view_model_handle_state_loading() {
        `when`(getAlbumUseCase.getAlbums()).thenReturn(
            Single.just(
                Results.success(listOf())
            )
        )
        val testObserver = TestObserver<Results<List<AlbumModel>>>()
        viewModel.getAlbums().subscribe(testObserver)
        val testObserverLoadingState = TestObserver<Boolean>()
        viewModel.data
            .loading
            .toObservable()
            .subscribe(testObserverLoadingState)
        testObserverLoadingState.assertNoErrors()
    }


}