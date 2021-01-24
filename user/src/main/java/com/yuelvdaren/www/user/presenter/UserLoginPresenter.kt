package com.yuelvdaren.www.user.presenter

import com.ljb.android.comm.rx.subscribeNet
import com.yuelvdaren.www.user.R
import com.yuelvdaren.www.user.contract.UserLoginContract
import com.yuelvdaren.www.user.model.UserLoginModel
import mvp.ljb.kt.presenter.BaseMvpPresenter
import net.ljb.kt.utils.RxUtils

/**
 * @Author Kotlin MVP Plugin
 * @Date 2021/01/23
 * @Description input description
 **/
class UserLoginPresenter : BaseMvpPresenter<UserLoginContract.IView, UserLoginContract.IModel>(),
    UserLoginContract.IPresenter {

    override fun registerModel() = UserLoginModel::class.java

    override fun login(userName: String, pwd: String) {
        if (userName.isEmpty() || pwd.isEmpty()) {
            getMvpView().showToast(R.string.user_input_has_null)
            return
        }
        getModel().login(userName, pwd)
            .compose(RxUtils.bindToLife(getMvpView()))
            .compose(RxUtils.schedulerIO2Main())
            .subscribeNet(getMvpView(), true) {

                onNextEx {
                    if ("0" == it.errorCode) {
                        getMvpView().showToast(R.string.user_login_success)
                        getMvpView().onLoginSuccess(it)
                    } else {
                        getMvpView().showToast(it.errorMsg)
                    }
                }

            }
    }

}
