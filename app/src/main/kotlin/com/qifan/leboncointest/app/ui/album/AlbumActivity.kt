package com.qifan.leboncointest.app.ui.album

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.qifan.leboncointest.R
import com.qifan.leboncointest.app.base.ReactiveBehavior
import com.qifan.leboncointest.app.base.ReactiveBehaviorDelegate
import com.qifan.leboncointest.app.base.activity.InjectionActivity
import com.qifan.leboncointest.app.extension.mainThread
import com.qifan.leboncointest.app.extension.subscribeAndLogError
import com.qifan.leboncointest.domain.exception.LeBonCoinException
import com.qifan.leboncointest.domain.model.AlbumModel
import com.qifan.leboncointest.domain.model.Results
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.android.synthetic.main.album_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumActivity : InjectionActivity(),
    ReactiveBehavior by ReactiveBehaviorDelegate() {

    private val viewModel: AlbumViewModel by viewModel()
    private lateinit var albumAdapter: AlbumAdapter

    override fun getLayoutId(): Int = R.layout.album_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        compositeDisposable.addAll(
            fetchData().subscribeAndLogError(),
            handleDataError().subscribeAndLogError(),
            handleDataSuccess().subscribeAndLogError(),
            handleDataLoading().subscribeAndLogError()
        )
    }

    private fun setUpRecyclerView() {
        rv_album.apply {
            albumAdapter = AlbumAdapter()
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(this@AlbumActivity)
        }
    }

    private fun fetchData(): Single<Results<List<AlbumModel>>> {
        return viewModel.getAlbums()
            .mainThread()
    }


    private fun handleDataError(): Flowable<Pair<Boolean, Throwable?>> {
        return viewModel.data
            .hasError
            .mainThread()
            .doOnNext { (hasError, error) ->
                if (hasError) {
                    displayError(error)
                }
            }
    }

    private fun handleDataLoading(): Flowable<Boolean> {
        return viewModel.data
            .loading
            .mainThread()
            .doOnNext(::displayLoading)
    }

    private fun handleDataSuccess(): Flowable<Results<List<AlbumModel>>> {
        return viewModel.data
            .success
            .mainThread()
            .doOnNext {
                when (it) {
                    is Results.Success -> updateRecyclerView(it.data)
                    is Results.Failure -> displayError(it.error)
                }
            }
    }

    private fun displayError(error: Throwable?) {
        val stringId = when (error) {
            is LeBonCoinException.EmptyException -> R.string.empty_error
            is LeBonCoinException.NetworkException -> R.string.network_error
            else -> R.string.general_error
        }
        Toast.makeText(this, getString(stringId), Toast.LENGTH_LONG)
            .show()
    }


    private fun updateRecyclerView(data: List<AlbumModel>) {
        loader.visibility = View.GONE
        rv_album.visibility = View.VISIBLE
        albumAdapter.setData(data)
    }

    private fun displayLoading(show: Boolean) {
        if (show) {
            loader.visibility = View.VISIBLE
            rv_album.visibility = View.GONE
        } else {
            loader.visibility = View.GONE
            rv_album.visibility = View.VISIBLE
        }
    }
}
