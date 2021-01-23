package com.yuelvdaren.www.user.contract

import com.yuelvdaren.www.user.bean.LoginBean
import io.reactivex.rxjava3.core.Observable
import mvp.ljb.kt.contract.IPresenterContract
import mvp.ljb.kt.contract.IViewContract
import mvp.ljb.kt.contract.IModelContract

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
interface UserRegisterContract {

    interface IView : IViewContract {
        fun showLoading()
        fun hideLoading()
        fun onRegisterUserSuccess(it: LoginBean)
    }

    interface IPresenter : IPresenterContract {
        fun registerUser(userName: String, pwd: String, confirmPwd: String)
    }

    interface IModel : IModelContract {
        fun registerUser(userName: String, pwd: String, confirmPwd: String):Observable<LoginBean>
    }
}
