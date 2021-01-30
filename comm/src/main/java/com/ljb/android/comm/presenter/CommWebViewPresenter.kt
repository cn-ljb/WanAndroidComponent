package com.ljb.android.comm.presenter

import mvp.ljb.kt.presenter.BaseMvpPresenter
import com.ljb.android.comm.contract.CommWebViewContract
import com.ljb.android.comm.model.CommWebViewModel

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/30
 * @Description input description
 **/
class CommWebViewPresenter : BaseMvpPresenter<CommWebViewContract.IView, CommWebViewContract.IModel>(), CommWebViewContract.IPresenter{

    override fun registerModel() = CommWebViewModel::class.java

}
