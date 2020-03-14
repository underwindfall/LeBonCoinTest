package com.qifan.leboncointest.app.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.qifan.leboncointest.R
import com.qifan.leboncointest.app.extension.mainThread
import com.qifan.leboncointest.app.extension.subscribeAndLogError
import com.qifan.leboncointest.domain.usecase.album.GetAlbumUseCase
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent

class MainFragment : Fragment(), KoinComponent {
    private val useCase: GetAlbumUseCase by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        useCase.getAlbums()
            .mainThread()
            .subscribe ({
                Log.d("Qifan","$it")
            },{
                Log.d("Qifan","Something is wrong" +
                        "")
            })

    }

}
