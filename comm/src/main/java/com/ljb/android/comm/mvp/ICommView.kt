package com.ljb.android.comm.mvp

import mvp.ljb.kt.contract.IViewContract

interface ICommView : IViewContract {

    fun showLoading()

    fun hideLoading()

}