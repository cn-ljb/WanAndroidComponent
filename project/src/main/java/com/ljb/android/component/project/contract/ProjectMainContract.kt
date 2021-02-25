package com.ljb.android.component.project.contract

import com.ljb.android.comm.mvp.ICommView
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/02/26
 * @Description input description
 **/
interface ProjectMainContract {

    interface IView : ICommView

    interface IPresenter : IPresenterContract

    interface IModel : IModelContract
}
