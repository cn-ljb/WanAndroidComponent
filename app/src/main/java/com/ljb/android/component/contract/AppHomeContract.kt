package com.ljb.android.component.contract

import com.ljb.android.comm.mvp.ICommView
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
interface AppHomeContract {

    interface IView : ICommView

    interface IPresenter : IPresenterContract

    interface IModel : IModelContract
}
