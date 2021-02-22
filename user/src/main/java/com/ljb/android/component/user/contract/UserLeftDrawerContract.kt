package com.ljb.android.component.user.contract

import com.ljb.android.comm.bean.base.HttpBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/25
 * @Description input description
 **/
interface UserLeftDrawerContract {

    interface IView : IViewContract {
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
