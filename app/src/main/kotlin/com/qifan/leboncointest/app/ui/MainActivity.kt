package com.qifan.leboncointest.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qifan.leboncointest.R
import com.qifan.leboncointest.app.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment())
                .commitNow()
        }
    }
}
