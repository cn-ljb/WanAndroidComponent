package com.ljb.android.component.user.contract

import com.ljb.android.comm.bean.base.HttpBean
import com.ljb.android.comm.mvp.ICommView
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/25
 * @Description input description
 **/
interface UserLeftDrawerContract {

    interface IView : ICommView {
        fun onLogoutSuccess()
    }

    interface IPresenter : IPresenterContract {
        fun logout()
    }

    interface IModel : IModelContract {
        fun logout(): Observable<HttpBean>
        fun clearUserToSP(httpBean: HttpBean): HttpBean
    }
}
