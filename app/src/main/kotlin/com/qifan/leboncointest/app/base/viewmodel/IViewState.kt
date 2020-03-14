package com.qifan.leboncointest.app.base.viewmodel

interface IViewState {

    enum class State {
        INIT,
        LOADING,
        SUCCESS,
        ERROR
    }
}