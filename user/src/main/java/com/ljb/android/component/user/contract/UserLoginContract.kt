package com.ljb.android.component.user.contract

import com.ljb.android.comm.mvp.ICommView
import com.ljb.android.component.user.bean.LoginBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IModelContract
import mvp.ljb.kt.contract.IPresenterContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
interface UserLoginContract {

    interface IView : ICommView {
        fun onLoginSuccess(json: String)
    }

    interface IPresenter : IPresenterContract {
        fun login(userName: String, pwd: String)
    }

    interface IModel : IModelContract {
        fun login(userName: String, pwd: String): Observable<LoginBean>
        fun saveLoginToSP(loginBean: LoginBean): String
        fun checkHeaderUrl(loginBean: LoginBean): LoginBean
    }
}
