package com.ljb.android.comm.contract

import com.ljb.android.comm.mvp.ICommView
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/30
 * @Description input description
 **/
interface CommWebViewContract {

    interface IView : ICommView

    interface IPresenter : IPresenterContract

    interface IModel : IModelContract
}
